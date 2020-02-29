package com.br.liferay.sales.taxes.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Para entender melhor as APIs criadas acesse http://localhost:8080/swagger-ui.html
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SalesTaxesCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesTaxesCalculatorApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				 registry.addMapping("/**");
			}
		};
	}

}
