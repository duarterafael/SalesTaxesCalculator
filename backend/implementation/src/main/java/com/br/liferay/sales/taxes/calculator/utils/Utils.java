package com.br.liferay.sales.taxes.calculator.utils;

public class Utils {
	public static double round(double input)
	{
		return Math.round(100 * input) / 100.0;
	}
	
	public static double ceil(double input)
	{
		return Math.ceil(input * 20.0) / 20.0;
	}
}
