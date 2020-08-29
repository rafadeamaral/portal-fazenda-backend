package br.com.amaral.portal.fazenda.servico.repository;

import br.com.amaral.portal.fazenda.servico.domain.ServicoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoHistoricoRepository extends JpaRepository<ServicoHistorico, Long> {

}
