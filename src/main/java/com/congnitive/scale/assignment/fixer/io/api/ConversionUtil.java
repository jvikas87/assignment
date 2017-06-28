package com.congnitive.scale.assignment.fixer.io.api;

import org.springframework.web.client.RestTemplate;

import com.congnitive.scale.assignment.pojo.ConversionInfo;

public class ConversionUtil {

	private ConversionUtil() {

	}

	public static ConversionInfo getConversionInfo(String base) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://api.fixer.io/latest?base=" + base, ConversionInfo.class);
	}

}
