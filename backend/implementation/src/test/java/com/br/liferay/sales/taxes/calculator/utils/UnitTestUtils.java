package com.br.liferay.sales.taxes.calculator.utils;

import java.util.Random;

public class UnitTestUtils {
	public static double randomDouble(double min, double max, int stepsize) {
	    if(max < min || stepsize < 0)
	    {
	    	throw new IllegalArgumentException();
	    }
		Random r = new Random();
	    return (r.nextInt((int)((max-min)*10+1))+min*10) / (stepsize*10.0);
	}

}
