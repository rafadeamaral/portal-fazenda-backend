package br.com.amaral.portal.fazenda.autorizador.repository;

import br.com.amaral.portal.fazenda.autorizador.domain.Autorizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizadorRepository extends JpaRepository<Autorizador, Integer> {

    Autorizador findByDsAutorizadorIgnoreCase(String dsAutorizador);

}
