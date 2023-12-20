package br.com.dacatividade3.dacatividade3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.dacatividade3.dacatividade3.repositories")
@EntityScan(basePackages = "br.com.dacatividade3.dacatividade3.entities")
public class DacAtividade3Application {

	public static void main(String[] args) {
		SpringApplication.run(DacAtividade3Application.class, args);
	}

}
