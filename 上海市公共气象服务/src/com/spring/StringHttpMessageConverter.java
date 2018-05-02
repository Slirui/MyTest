package com.spring;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;

public class StringHttpMessageConverter extends org.springframework.http.converter.StringHttpMessageConverter {

	public StringHttpMessageConverter() {
		super();
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return super.getSupportedMediaTypes();
	}

	@Override
	public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {

		super.setSupportedMediaTypes(supportedMediaTypes);
	}

	@Override
	protected List<Charset> getAcceptedCharsets() {

		return super.getAcceptedCharsets();
	}

	@Override
	protected Long getContentLength(String arg0, MediaType arg1) {

		return super.getContentLength(arg0, arg1);
	}

	@Override
	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage) throws IOException {

		return super.readInternal(clazz, inputMessage);
	}

	@Override
	public void setWriteAcceptCharset(boolean writeAcceptCharset) {

		super.setWriteAcceptCharset(writeAcceptCharset);
	}

	@Override
	protected void writeInternal(String str, HttpOutputMessage outputMessage) throws IOException {

		super.writeInternal(str, outputMessage);
	}

}
