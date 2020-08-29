package br.com.amaral.portal.fazenda.servico.endpoint;

import br.com.amaral.portal.fazenda.servico.domain.ServicoStatusWrapper;
import br.com.amaral.portal.fazenda.servico.service.ServicoStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("servico/status")
public class ServicoStatusEndpoint {

    @Autowired
    private ServicoStatusService servicoStatusService;

    @GetMapping
    public ResponseEntity<List<ServicoStatusWrapper>> findByStatusAtual() {

        return ResponseEntity.ok(servicoStatusService.findByStatusAtual());
    }

    @GetMapping("autorizador")
    public ResponseEntity<ServicoStatusWrapper> findByAutorizador(@RequestParam String dsAutorizador) {

        return ResponseEntity.ok(servicoStatusService.findByAutorizador(dsAutorizador));
    }

}
