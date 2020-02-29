package com.br.liferay.sales.taxes.calculator.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "erro.msg")
@Getter
@Setter
public class ErrorMsgProperties { 
	
    private String productTypeNotFount; 
}