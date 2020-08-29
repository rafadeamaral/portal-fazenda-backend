package br.com.amaral.portal.fazenda.servico.service;

import br.com.amaral.portal.fazenda.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.servico.domain.ServicoStatusWrapper;
import br.com.amaral.portal.fazenda.servico.domain.ServicoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoStatusService {

    @Autowired
    private ServicoHistoricoService servicoHistoricoService;

    public List<ServicoStatusWrapper> findByStatusAtual() {

        List<ServicoHistorico> historicos = servicoHistoricoService.findByStatusAtual();

        var autorizadores = historicos.stream().map(ServicoHistorico::getAutorizador).collect(Collectors.toSet());

        List<ServicoStatusWrapper> status = new ArrayList<>();

        autorizadores.stream().sorted(Comparator.comparing(Autorizador::getDsAutorizador)).forEach(autorizador -> {

            var servicos = historicos.stream()
                    .filter(servicoHistorico -> servicoHistorico.getAutorizador().equals(autorizador))
                    .map(this::toServicoWrapper).collect(Collectors.toList());

            var wrapper = new ServicoStatusWrapper();
            wrapper.setAutorizador(autorizador);
            wrapper.setServicos(servicos);

            status.add(wrapper);
        });

        return status;
    }

    private ServicoWrapper toServicoWrapper(ServicoHistorico servicoHistorico) {

        var servico = servicoHistorico.getServico();

        var wrapper = new ServicoWrapper();
        wrapper.setIdServico(servico.getIdServico());
        wrapper.setDsServico(servico.getDsServico());
        wrapper.setStatus(servicoHistorico.getStatus());

        return wrapper;
    }

}
