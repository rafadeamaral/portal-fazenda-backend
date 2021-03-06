package br.com.amaral.portal.fazenda.core.servico.domain;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "servico_historico")
public class ServicoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico_historico")
    private Long idServicoHistorico;

    @ManyToOne
    @JoinColumn(name = "id_autorizador")
    private Autorizador autorizador;

    @ManyToOne
    @JoinColumn(name = "id_servico")
    private Servico servico;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tp_status")
    private ServicoStatus status;

    @Column(name = "dh_historico")
    private LocalDateTime dhHistorico;

}
