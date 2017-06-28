package com.congnitive.scale.assignment.test;

import org.junit.Test;

import com.congnitive.scale.assignment.fixer.io.api.ConversionUtil;
import com.congnitive.scale.assignment.pojo.ConversionInfo;

public class Demo {

	@Test
	public void test(){
		ConversionInfo conversionInfo = ConversionUtil
				.getConversionInfo("INR");
		Double factor = conversionInfo.getRates().get("USD");
	}
}
