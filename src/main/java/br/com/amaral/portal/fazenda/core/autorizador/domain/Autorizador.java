package br.com.amaral.portal.fazenda.core.autorizador.domain;

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
@Entity(name = "autorizador")
public class Autorizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorizador")
    private Integer idAutorizador;

    @Column(name = "ds_autorizador")
    private String dsAutorizador;

}
