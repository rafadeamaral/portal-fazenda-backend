package br.com.amaral.portal.fazenda.servico.service;

import br.com.amaral.portal.fazenda.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.autorizador.service.AutorizadorService;
import br.com.amaral.portal.fazenda.servico.domain.Servico;
import br.com.amaral.portal.fazenda.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.servico.domain.ServicoStatus;
import br.com.amaral.portal.fazenda.servico.repository.ServicoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ServicoService {

    @Value("${portal.fazenda.endpoint}")
    private String endpoint;

    @Value("${portal.fazenda.dados.class}")
    private String dadosClass;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private AutorizadorService autorizadorService;

    @Autowired
    private ServicoHistoricoService servicoHistoricoService;

    public void synchronize() {

        try {

            var doc = Jsoup.connect(endpoint).get();

            var tabela = doc.body().getElementsByClass(dadosClass).first();

            var linhas = tabela.getElementsByTag("tr").iterator();

            var nomesServico = linhas.next().children().stream()
                    .map(Element::text).collect(Collectors.toList());

            List<List<String>> status = toStatus(linhas);

            removeTempo(nomesServico, status);

            var servicos = toServico(nomesServico);

            var historicos = toHistorico(status, servicos);

            servicoHistoricoService.save(historicos);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private List<ServicoHistorico> toHistorico(List<List<String>> status, List<Servico> servicos) {

        LocalDateTime dhHistorico = LocalDateTime.now();

        List<ServicoHistorico> historicos = new ArrayList<>();

        for (List<String> list : status) {

            Autorizador autorizador = autorizadorService.toAutorizador(list.remove(0));

            for (int i = 0; i < list.size(); i++) {

                var servico = servicos.get(i);
                var servicoStatus = toStatus(list.get(i));

                ServicoHistorico servicoHistorico = new ServicoHistorico();
                servicoHistorico.setAutorizador(autorizador);
                servicoHistorico.setServico(servico);
                servicoHistorico.setStatus(servicoStatus);
                servicoHistorico.setDhHistorico(dhHistorico);

                historicos.add(servicoHistorico);
            }
        }

        return historicos;
    }

    private ServicoStatus toStatus(String nome) {

        ServicoStatus servicoStatus = null;

        if (nonNull(nome)) {

            nome = nome.toLowerCase();

            if (nome.contains("verde")) {

                servicoStatus = ServicoStatus.VERDE;

            } else if (nome.contains("amarel")) {

                servicoStatus = ServicoStatus.AMARELO;

            } else if (nome.contains("vermelh")) {

                servicoStatus = ServicoStatus.VERMELHO;

            }
        }

        return servicoStatus;
    }

    private List<Servico> toServico(List<String> nomesServico) {

        nomesServico.remove(0);

        return nomesServico.stream().map(this::toServico).collect(Collectors.toList());
    }

    private Servico toServico(String nome) {

        var servico = servicoRepository.findByDsServicoIgnoreCase(nome);

        if (isNull(servico)) {

            servico = new Servico();
            servico.setDsServico(nome);

            servico = servicoRepository.save(servico);
        }

        return servico;
    }

    private List<List<String>> toStatus(Iterator<Element> linhas) {

        List<List<String>> lista = new ArrayList<>();

        while (linhas.hasNext()) {

            Element element = linhas.next();

            var status = element.children().stream()
                    .map(this::toStatus).collect(Collectors.toList());

            lista.add(status);
        }

        return lista;
    }

    private String toStatus(Element element) {

        var img = element.getElementsByTag("img").first();

        String src;

        if (nonNull(img)) {
            src = img.attr("src");
        } else {
            src = element.text();
        }

        return src;
    }

    private void removeTempo(List<String> nomesServico, List<List<String>> status) {

        var tempo = nomesServico.stream().filter(servico -> servico.toLowerCase().contains("tempo")).findFirst().orElse(null);

        if (nonNull(tempo)) {

            var index = nomesServico.indexOf(tempo);

            status.forEach(o -> o.remove(index));

            nomesServico.remove(index);
        }
    }

}
