package com.br.liferay.sales.taxes.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//I know that in a production environment with access to the swagger it is not a good practice, for security reasons. 
//However, I will abstract this for this project.
//To understand the APIs, please access swagger: <url>/swagger-ui.html (i.e: http://localhost:8080/swagger-ui.html)
@SpringBootApplication
@ConfigurationPropertiesScan("custom.properties")
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
