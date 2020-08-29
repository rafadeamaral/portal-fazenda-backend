package br.com.amaral.portal.fazenda.core.servico.wrapper;

import br.com.amaral.portal.fazenda.core.servico.domain.Servico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicoWrapper {

    private Servico servico;

    private List<ServicoStatusWrapper> status;

}
