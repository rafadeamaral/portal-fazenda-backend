package br.com.amaral.portal.fazenda.core.autorizador.service;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.core.autorizador.repository.AutorizadorRepository;
import br.com.amaral.portal.fazenda.core.servico.domain.ServicoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AutorizadorService {

    @Autowired
    private AutorizadorRepository autorizadorRepository;

    public Autorizador toAutorizador(String nome) {

        var autorizador = autorizadorRepository.findByDsAutorizadorIgnoreCase(nome);

        if (isNull(autorizador)) {

            autorizador = new Autorizador();
            autorizador.setDsAutorizador(nome);

            autorizador = autorizadorRepository.save(autorizador);
        }

        return autorizador;
    }

    public List<Autorizador> findByIndisponibilidade() {

        return autorizadorRepository.findByIndisponibilidade(ServicoStatus.VERMELHO.ordinal(), ServicoStatus.AMARELO.ordinal());
    }

    public List<Autorizador> findAll() {

        return autorizadorRepository.findAll();
    }

}
