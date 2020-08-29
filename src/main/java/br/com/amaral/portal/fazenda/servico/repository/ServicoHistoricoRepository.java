package br.com.amaral.portal.fazenda.servico.repository;

import br.com.amaral.portal.fazenda.servico.domain.ServicoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoHistoricoRepository extends JpaRepository<ServicoHistorico, Long> {

    @Query(value = "select * from servico_historico where id_servico_historico in (" +
            "select max(id_servico_historico) from servico_historico group by id_autorizador, id_servico)", nativeQuery = true)
    List<ServicoHistorico> findByStatusAtual();

}
