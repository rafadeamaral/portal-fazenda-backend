package br.com.amaral.portal.fazenda.core.servico.endpoint;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.core.autorizador.service.AutorizadorService;
import br.com.amaral.portal.fazenda.core.servico.domain.ServicoHistorico;
import br.com.amaral.portal.fazenda.core.servico.service.ServicoHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("servico/status")
public class ServicoStatusEndpoint {

    @Autowired
    private ServicoHistoricoService servicoHistoricoService;

    @Autowired
    private AutorizadorService autorizadorService;

    @GetMapping
    public ResponseEntity<List<ServicoHistorico>> findByStatusAtual() {

        return ResponseEntity.ok(servicoHistoricoService.findByStatusAtual());
    }

    @GetMapping("autorizador/{idAutorizador}")
    public ResponseEntity<List<ServicoHistorico>> findByAutorizador(@PathVariable Integer idAutorizador) {

        return ResponseEntity.ok(servicoHistoricoService.findByAutorizador(idAutorizador));
    }

    @GetMapping("periodo")
    public ResponseEntity<Page<ServicoHistorico>> findByPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dhInicio,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dhFim,
                                                                Pageable pageable) {

        return ResponseEntity.ok(servicoHistoricoService.findByPeriodo(dhInicio, dhFim, pageable));
    }

    @GetMapping("indisponibilidade")
    public List<Autorizador> findByIndisponibilidade() {

        return autorizadorService.findByIndisponibilidade();
    }

}
