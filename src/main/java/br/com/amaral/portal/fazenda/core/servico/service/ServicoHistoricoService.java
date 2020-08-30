package br.com.amaral.portal.fazenda.core.servico.service;

import br.com.amaral.portal.fazenda.core.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.core.servico.repository.ServicoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicoHistoricoService {

    @Autowired
    private ServicoHistoricoRepository servicoHistoricoRepository;

    public void save(List<ServicoHistorico> historicos) {

        servicoHistoricoRepository.saveAll(historicos);
    }

    public List<ServicoHistorico> findByStatusAtual() {

        return servicoHistoricoRepository.findByStatusAtual();
    }

    public List<ServicoHistorico> findByAutorizador(Integer idAutorizador) {

        return servicoHistoricoRepository.findByAutorizador(idAutorizador);
    }

    public List<ServicoHistorico> findByPeriodo(LocalDateTime dhInicio, LocalDateTime dhFim) {

        return servicoHistoricoRepository.findByPeriodo(dhInicio, dhFim);
    }

}
