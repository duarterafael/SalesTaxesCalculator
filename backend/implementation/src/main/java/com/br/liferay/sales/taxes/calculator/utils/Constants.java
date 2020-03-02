package com.br.liferay.sales.taxes.calculator.utils;

public final class Constants {
	
	//TODO: Put this in a properties file
	public static final double IMPORTED_PRODUCT_TAX = 5; 
	
	//Generic field sizes
	public static final int ID_MIN_VALUE = 0;
	
	//Product type fields sizes
	public static final int PRODUCT_TYPE_NAME_MIN_LENGTH = 3;
	public static final int PRODUCT_TYPE_NAME_MAX_LENGTH = 30;
	public static final String PRODUCT_TYPE_TAX_MIN_LENGTH = "0.00";
	public static final String PRODUCT_TYPE_TAX_MAX_LENGTH = "100.00";
	public static final int PRODUCT_NAME_MIN_LENGTH = 3;
	public static final int PRODUCT_NAME_MAX_LENGTH = 50;
	
	//URLS
	public static final String BASE_URL = "/api/v1";
	public static final String PRODUCT_URL = BASE_URL+"/products";
	public static final String PRODUCT_TYPE_URL = PRODUCT_URL+"/types";
	public static final String SALES_TAXR_URL = BASE_URL+"/sales/taxes";
	
	public static final String BLANCK_SPACE = " ";
	public static final String IMPORTED_PRODUCT_DESCRIPTION = "imported";
	public static final String COLONS = ":";
	public static final String SALES_TAXES_LABEL = "Sales Taxes: ";
	public static final String TOTAL_LABEL = "Total: ";
	
}
