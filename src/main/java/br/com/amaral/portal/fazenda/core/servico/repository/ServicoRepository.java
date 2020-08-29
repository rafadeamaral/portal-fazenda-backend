package br.com.amaral.portal.fazenda.core.servico.repository;

import br.com.amaral.portal.fazenda.core.servico.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    Servico findByDsServicoIgnoreCase(String dsServico);

}
