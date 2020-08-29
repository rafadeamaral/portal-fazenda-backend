package br.com.amaral.portal.fazenda.core.servico.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Integer idServico;

    @Column(name = "ds_servico")
    private String dsServico;

}
