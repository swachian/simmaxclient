package com.sanss.restclient;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class RestClient {
	private String url_base = "";
	public RestClient() {
		
	}
	
	public RestClient(String u) {
		this.url_base = u;
	}
	
	public RestResponse post(String url, List params) throws HttpException, IOException {
		PostMethod postMethod = new PostMethod(url_base+url);
		
		NameValuePair[] data = new NameValuePair[params.size()];
		for (int i=0; i < params.size(); i++) {
			NameValuePair np = (NameValuePair) params.get(i);
			data[i] = np;
		}

		postMethod.setRequestBody(data);

		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(postMethod);

		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + postMethod.getStatusLine());
			return null;
		}
		
		RestResponse response = new RestResponse();
		byte[] responseBody = postMethod.getResponseBody();
		response.setCode(postMethod.getStatusCode());
		response.setBody( new String(responseBody, "UTF-8") );
		return response;
	}

}
