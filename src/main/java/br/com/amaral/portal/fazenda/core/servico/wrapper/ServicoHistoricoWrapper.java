package br.com.amaral.portal.fazenda.core.servico.wrapper;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicoHistoricoWrapper {

    private Autorizador autorizador;

    private List<ServicoWrapper> servicos;

}
