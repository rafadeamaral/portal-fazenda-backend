package br.com.amaral.portal.fazenda.core.servico.service;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.core.servico.domain.Servico;
import br.com.amaral.portal.fazenda.core.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.core.servico.wrapper.ServicoHistoricoWrapper;
import br.com.amaral.portal.fazenda.core.servico.wrapper.ServicoStatusWrapper;
import br.com.amaral.portal.fazenda.core.servico.wrapper.ServicoWrapper;
import br.com.amaral.portal.fazenda.core.servico.repository.ServicoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoHistoricoService {

    @Autowired
    private ServicoHistoricoRepository servicoHistoricoRepository;

    public void save(List<ServicoHistorico> historicos) {

        servicoHistoricoRepository.saveAll(historicos);
    }

    public List<ServicoHistoricoWrapper> findByStatusAtual() {

        List<ServicoHistorico> historicos = servicoHistoricoRepository.findByStatusAtual();

        return toServicoHistoricoWrapper(historicos);
    }

    private List<ServicoHistoricoWrapper> toServicoHistoricoWrapper(List<ServicoHistorico> historicos) {

        var autorizadores = historicos.stream().map(ServicoHistorico::getAutorizador).collect(Collectors.toSet());

        List<ServicoHistoricoWrapper> status = new ArrayList<>();

        autorizadores.stream().sorted(Comparator.comparing(Autorizador::getDsAutorizador)).forEach(autorizador -> {

            var historicosAutorizador = historicos.stream()
                    .filter(servicoHistorico -> servicoHistorico.getAutorizador().equals(autorizador))
                    .collect(Collectors.toList());

            var wrapper = toServicoHistoricoWrapper(autorizador, historicosAutorizador);

            status.add(wrapper);
        });

        return status;
    }

    private ServicoWrapper toServicoWrapper(Servico servico, List<ServicoHistorico> historicos) {

        var status = historicos.stream().filter(servicoHistorico -> servicoHistorico.getServico().equals(servico))
                .map(this::toServicoStatusWrapper).collect(Collectors.toList());

        var wrapper = new ServicoWrapper();
        wrapper.setServico(servico);
        wrapper.setStatus(status);

        return wrapper;
    }

    private ServicoStatusWrapper toServicoStatusWrapper(ServicoHistorico servicoHistorico) {

        var wrapper = new ServicoStatusWrapper();
        wrapper.setStatus(servicoHistorico.getStatus());
        wrapper.setDhHistorico(servicoHistorico.getDhHistorico());

        return wrapper;
    }

    public ServicoHistoricoWrapper findByAutorizador(String dsAutorizador) {

        List<ServicoHistorico> historicos = servicoHistoricoRepository.findByAutorizador(dsAutorizador);

        if (historicos.isEmpty()) {

            return null;
        }

        return toServicoHistoricoWrapper(historicos.get(0).getAutorizador(), historicos);
    }

    private ServicoHistoricoWrapper toServicoHistoricoWrapper(Autorizador autorizador, List<ServicoHistorico> historicos) {

        var servicos = historicos.stream().map(ServicoHistorico::getServico)
                .map(servico -> toServicoWrapper(servico, historicos)).collect(Collectors.toList());

        var wrapper = new ServicoHistoricoWrapper();
        wrapper.setAutorizador(autorizador);
        wrapper.setServicos(servicos);

        return wrapper;
    }

    public List<ServicoHistoricoWrapper> findByPeriodo(LocalDateTime dhInicio, LocalDateTime dhFim) {

        List<ServicoHistorico> historicos = servicoHistoricoRepository.findByDhHistoricoBetweenOrderByDhHistoricoDesc(dhInicio, dhFim);

        return toServicoHistoricoWrapper(historicos);
    }

}
