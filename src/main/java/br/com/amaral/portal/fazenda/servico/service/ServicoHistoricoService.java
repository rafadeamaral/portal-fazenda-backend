package br.com.amaral.portal.fazenda.servico.service;

import br.com.amaral.portal.fazenda.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.servico.repository.ServicoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
