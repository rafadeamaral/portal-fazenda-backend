package br.com.amaral.portal.fazenda.core.servico.endpoint;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.core.autorizador.service.AutorizadorService;
import br.com.amaral.portal.fazenda.core.servico.service.ServicoHistoricoService;
import br.com.amaral.portal.fazenda.core.servico.wrapper.ServicoHistoricoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<ServicoHistoricoWrapper>> findByStatusAtual() {

        return ResponseEntity.ok(servicoHistoricoService.findByStatusAtual());
    }

    @GetMapping("autorizador")
    public ResponseEntity<ServicoHistoricoWrapper> findByAutorizador(@RequestParam String dsAutorizador) {

        return ResponseEntity.ok(servicoHistoricoService.findByAutorizador(dsAutorizador));
    }

    @GetMapping("perido")
    public ResponseEntity<List<ServicoHistoricoWrapper>> findByPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dhInicio,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dhFim) {

        return ResponseEntity.ok(servicoHistoricoService.findByPeriodo(dhInicio, dhFim));
    }

    @GetMapping("indisponibilidade")
    public List<Autorizador> findByIndisponibilidade() {

        return autorizadorService.findByIndisponibilidade();
    }

}