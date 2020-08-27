package br.com.amaral.portal.fazenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PortalFazendaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PortalFazendaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PortalFazendaApplication.class);
	}

}
