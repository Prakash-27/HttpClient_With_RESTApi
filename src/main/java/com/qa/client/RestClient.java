package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//GET Method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the GET URL
		return closeableHttpResponse;
	}

	//GET Method with Headers:
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the GET URL
		return closeableHttpResponse;
	}
	
	// 1. GET CALL Method
//	public void get(String url) {
//
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet httpGet = new HttpGet(url); // http get request
//		try {
//			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the GET URL
//
//			// a. Status Code:
//			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
//			System.out.println("Status Code---> " + statusCode);
//
//			// b. Json String
//			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
//
//			// c. Converting Json String to Actual Json Response Format where we gotten in
//			// Postman
//			JSONObject responseJSON = new JSONObject(responseString);
//			System.out.println("Response JSON from API---> " + responseJSON);
//
//			// d. All Headers
//			Header[] headersArray = closeableHttpResponse.getAllHeaders();
//			HashMap<String, String> allHeaders = new HashMap<String, String>();
//			for (Header header : headersArray) {
//				allHeaders.put(header.getName(), header.getValue());
//			}
//			System.out.println("Header Array--> " + allHeaders);
//			
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	
	//3. POST Method:
	//Four thing are need to POST CALL that is, i) POST CALL, ii) EndPoint url 
	//iii) Body in Postman on that we use to write our Json PayLoad to POST or Update our Entity in DataBase, iv) Header
	public CloseableHttpResponse post(String url, String entityJsonString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//i) POST CALL ii) EndPoint url are in below line
		HttpPost httpPost = new HttpPost(url); //http POST Request 
		//iii) Body in Postman/PayLoad
		httpPost.setEntity(new StringEntity(entityJsonString)); //for PayLoad
		//iv) for headers:
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}
	

}