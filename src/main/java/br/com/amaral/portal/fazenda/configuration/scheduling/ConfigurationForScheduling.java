package br.com.amaral.portal.fazenda.configuration.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@DependsOn("flyway")
public class ConfigurationForScheduling {

}
