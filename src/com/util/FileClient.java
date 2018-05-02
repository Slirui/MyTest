package com.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class FileClient {
	public static void download(String path, File storeFile) {
		HttpClient client = new HttpClient();
		GetMethod get = null;
		try {
			// hasActive();

			get = new GetMethod(path);
			int i = client.executeMethod(get);
			if (200 == i) {

				FileOutputStream output = new FileOutputStream(storeFile);
				// �õ�������Դ���ֽ�����,��д���ļ�
				output.write(get.getResponseBody());
				output.close();
			} else {
				System.out.println("no pic");
			}
		} catch (Exception e) {
			System.out.println("no pic");
		} finally {
			get.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
	}

	/**
	 * ���������л�ȡ�ֽ�����
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static byte[] download(String path) {
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// ���ó�ʱ��Ϊ3��
			conn.setConnectTimeout(3 * 1000);
			// ��ֹ���γ���ץȡ������403����
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			// �õ�������
			InputStream inputStream = conn.getInputStream();
			// ��ȡ�Լ�����
			byte[] getData = readInputStream(inputStream);

			if (inputStream != null) {

				inputStream.close();

			}

			return getData;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args)
	{
		try {
			download("http://10.228.19.122/"+java.net.URLEncoder.encode("DocBackFile/2016/12/22/�о�ίһ������չ��_201612220944.doc","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
