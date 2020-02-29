package com.br.liferay.sales.taxes.calculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomizedResponseException {
    private String errorMessage;
    private String statusCode;
}
