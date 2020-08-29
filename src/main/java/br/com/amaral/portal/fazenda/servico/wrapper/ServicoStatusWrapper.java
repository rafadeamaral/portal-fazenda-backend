package br.com.amaral.portal.fazenda.servico.wrapper;

import br.com.amaral.portal.fazenda.servico.domain.ServicoStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoStatusWrapper {

    private ServicoStatus status;

    private LocalDateTime dhHistorico;

}
