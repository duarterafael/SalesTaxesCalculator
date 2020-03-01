package com.br.liferay.sales.taxes.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {
	
	protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
