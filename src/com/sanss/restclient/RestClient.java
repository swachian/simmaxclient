package com.sanss.restclient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class RestClient {
	
	public RestResponse post(String url, Map params) throws HttpException, IOException {
		PostMethod postMethod = new PostMethod(url);
		
		NameValuePair[] data = new NameValuePair[params.size()];
		Iterator iter = params.entrySet().iterator();
		int i = 0;
		while(iter.hasNext()) {
			 Map.Entry entry = (Map.Entry)iter.next();
			 data[i++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
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

		response.setBody( new String(responseBody, "UTF-8") );
		return response;
	}

}
