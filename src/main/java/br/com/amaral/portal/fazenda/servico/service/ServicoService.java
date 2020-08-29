package br.com.amaral.portal.fazenda.servico.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ServicoService {

    @PostConstruct
    private void init() {

        try {

            var doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();

            var tabela = doc.body().getElementsByClass("tabelaListagemDados").first();

            var linhas = tabela.getElementsByTag("tr").iterator();

            var servicos = linhas.next().children().stream()
                    .map(Element::text).collect(Collectors.toList());

            List<List<String>> status = toStatus(linhas);

            removeTempo(servicos, status);

            servicos.size();

        } catch (IOException e) {

            e.printStackTrace();
        }
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

    private void removeTempo(List<String> servicos, List<List<String>> status) {

        var tempo = servicos.stream().filter(servico -> servico.toLowerCase().contains("tempo")).findFirst().orElse(null);

        if (nonNull(tempo)) {

            var index = servicos.indexOf(tempo);

            status.forEach(o -> o.remove(index));

            servicos.remove(index);
        }
    }

}
