package br.com.amaral.portal.fazenda.core.autorizador.repository;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorizadorRepository extends JpaRepository<Autorizador, Integer> {

    Autorizador findByDsAutorizadorIgnoreCase(String dsAutorizador);

    @Query(value = "select * from autorizador where id_autorizador in (select id_autorizador from servico_historico " +
            "where tp_status in (:status) group by id_autorizador having count(id_autorizador) = (select max(indisponibilidade) " +
            "from (select id_autorizador, count(id_autorizador) as indisponibilidade from servico_historico " +
            "where tp_status in (:status) group by id_autorizador)))",
            nativeQuery = true)
    List<Autorizador> findByIndisponibilidade(Integer... status);

}
