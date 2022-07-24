package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("ServiceURL");
		apiUrl = prop.getProperty("ApiURL");
		// https://reqres.in + /api/users

		url = serviceUrl + apiUrl;
	}

	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json"); // We have to use the forward slash / for application/json
											              // (or) application/XML to add the Header
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test123");
//		headerMap.put("Auth Token", "12345");

		// Java Object(POJO) --> Json Object by using Jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); //This my expected users Object

		// we are Marshaling Java Object to json file:
		mapper.writeValue(new File("src\\main\\java\\com\\qa\\data\\Users.json"), users);

		// This Also Called Marshaling Java Object to json in String:
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);

		// Then Hitting The POST CALL from RestClient Java Class
		closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);

		// Validate Response from API:
		// 1. Status code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

		// 2. JsonString:
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is: " + responseJson);

		// we are UnMarshaling Json to Java Object:
		Users usersResponseObj = mapper.readValue(responseString, Users.class); //actual users Object
		System.out.println(usersResponseObj);
		
		//System.out.println(users.getName().equals(usersResponseObj.getName()));
		Assert.assertTrue(users.getName().equals(usersResponseObj.getName()));
		
		//System.out.println(users.getJob().equals(usersResponseObj.getJob())); 
		Assert.assertTrue(users.getJob().equals(usersResponseObj.getJob()));
		
		System.out.println(usersResponseObj.getId());
		System.out.println(usersResponseObj.getCreatedAt());
		
	}

}
//HttpClient is More preferable than RestAssured -> Naveen
//Because of its amazing utility's HttpClient is More preferable
//Ultimately RestAssured Internally Calling HttpClient Only.