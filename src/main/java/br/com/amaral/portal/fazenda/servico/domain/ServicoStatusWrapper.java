package br.com.amaral.portal.fazenda.servico.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoStatusWrapper {

    private ServicoStatus status;

    private LocalDateTime dhHistorico;

}
