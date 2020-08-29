package br.com.amaral.portal.fazenda.servico.domain;

import br.com.amaral.portal.fazenda.autorizador.domain.Autorizador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicoHistoricoWrapper {

    private Autorizador autorizador;

    private List<ServicoWrapper> servicos;

}
