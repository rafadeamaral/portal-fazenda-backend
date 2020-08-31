package br.com.amaral.portal.fazenda.core.servico.repository;

import br.com.amaral.portal.fazenda.core.servico.domain.ServicoHistorico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServicoHistoricoRepository extends JpaRepository<ServicoHistorico, Long> {

    @Query(value = "from ServicoHistorico where idServicoHistorico in (select max(idServicoHistorico) from ServicoHistorico group by autorizador, servico)")
    List<ServicoHistorico> findByStatusAtual();

    @Query(value = "from ServicoHistorico where autorizador.idAutorizador = ?1 and idServicoHistorico in (select max(idServicoHistorico) from ServicoHistorico group by autorizador, servico)")
    List<ServicoHistorico> findByAutorizador(Integer idAutorizador);

    @Query(value = "from ServicoHistorico where dhHistorico between ?1 and ?2 order by autorizador, servico, dhHistorico desc",
            countQuery = "select count(idServicoHistorico) from ServicoHistorico where dhHistorico between ?1 and ?2")
    Page<ServicoHistorico> findByPeriodo(LocalDateTime dhInicio, LocalDateTime dhFim, Pageable pageable);

}
