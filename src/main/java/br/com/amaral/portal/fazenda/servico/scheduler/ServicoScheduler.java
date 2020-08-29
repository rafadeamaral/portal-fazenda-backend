package br.com.amaral.portal.fazenda.servico.scheduler;

import br.com.amaral.portal.fazenda.servico.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServicoScheduler {

    @Autowired
    private ServicoService servicoService;

    @Scheduled(fixedDelayString = "${scheduler.servico.delay}", initialDelay = 60000)
    public void synchronize() {

        servicoService.synchronize();
    }

}
