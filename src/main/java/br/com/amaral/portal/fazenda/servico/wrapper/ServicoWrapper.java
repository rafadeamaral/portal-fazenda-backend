package br.com.amaral.portal.fazenda.servico.wrapper;

import br.com.amaral.portal.fazenda.servico.domain.Servico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServicoWrapper {

    private Servico servico;

    private List<ServicoStatusWrapper> status;

}
