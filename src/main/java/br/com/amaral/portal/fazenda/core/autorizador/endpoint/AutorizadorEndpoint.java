package br.com.amaral.portal.fazenda.core.autorizador.endpoint;

import br.com.amaral.portal.fazenda.core.autorizador.domain.Autorizador;
import br.com.amaral.portal.fazenda.core.autorizador.service.AutorizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("autorizador")
public class AutorizadorEndpoint {

    @Autowired
    private AutorizadorService autorizadorService;

    @GetMapping
    public ResponseEntity<List<Autorizador>> findAll() {

        return ResponseEntity.ok(autorizadorService.findAll());
    }

}
