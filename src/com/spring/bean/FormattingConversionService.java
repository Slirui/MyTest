package com.spring.bean;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattingConversionService extends org.springframework.format.support.FormattingConversionService {
	public FormattingConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
		// Ensure @NumberFormat is still supported
		conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());
		// Register date conversion with a specific global format
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
	}
}
