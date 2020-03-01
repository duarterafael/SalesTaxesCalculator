package com.br.liferay.sales.taxes.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {
	
	public final static String BASE_URL = "/api/v1/";
	
	protected String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
