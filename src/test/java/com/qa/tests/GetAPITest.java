package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Utils.Test_Utils;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {

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

	@Test(priority = 1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);

		// a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. Json String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// c. Converting Json String to Actual Json Response Format where we gotten in
		// Postman
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON from API---> " + responseJSON);

		// Single Value Assertion:
		// per_page:
		String perPageValue = Test_Utils.getValueByJPath(responseJSON, "/per_page"); // The /per_page string is comes
																						// from Json Response we
																						// executed and the we are
																						// validating Json Using
																						// Jsonlint.com website.
		System.out.println("Value of Per Page is---> " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total:
		String totalValue = Test_Utils.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of Total is---> " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON ARRAY:
		String lastName = Test_Utils.getValueByJPath(responseJSON, "/data[0]/last_name");
		String iD = Test_Utils.getValueByJPath(responseJSON, "/data[0]/id");
		String Avatar = Test_Utils.getValueByJPath(responseJSON, "/data[0]/avatar");
		String firstName = Test_Utils.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println("Json Data[0] Index Zero lastName Value: " + lastName);
		System.out.println("Json Data[0] Index Zero id Value: " + iD);
		System.out.println("Json Data[0] Index Zero avatar Value: " + Avatar);
		System.out.println("Json Data[0] Index Zero firstName Value: " + firstName);

		Assert.assertEquals(lastName, "Bluth");
		Assert.assertEquals(Integer.parseInt(iD), 1);
		Assert.assertEquals(Avatar, "https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(firstName, "George");

		// d. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array--> " + allHeaders);

	}

	@Test(priority = 2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json"); //We have to use the forward slash / for application/json (or) application/XML to add the Header
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test123");
//		headerMap.put("Auth Token", "12345");

		closeableHttpResponse = restClient.get(url);

		// a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. Json String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// c. Converting Json String to Actual Json Response Format where we gotten in
		// Postman
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response JSON from API---> " + responseJSON);

		// Single Value Assertion:
		// per_page:
		// The /per_page string is comes from Json Response we executed in the Postman and then we are validating full Json Response Using Jsonlint.com website.
		String perPageValue = Test_Utils.getValueByJPath(responseJSON, "/per_page"); 
		System.out.println("Value of Per Page is---> " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total:
		String totalValue = Test_Utils.getValueByJPath(responseJSON, "/total");
		System.out.println("Value of Total is---> " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON ARRAY:
		String lastName = Test_Utils.getValueByJPath(responseJSON, "/data[0]/last_name");
		String iD = Test_Utils.getValueByJPath(responseJSON, "/data[0]/id");
		String Avatar = Test_Utils.getValueByJPath(responseJSON, "/data[0]/avatar");
		String firstName = Test_Utils.getValueByJPath(responseJSON, "/data[0]/first_name");

		System.out.println("Json Data[0] Index Zero lastName Value: " + lastName);
		System.out.println("Json Data[0] Index Zero id Value: " + iD);
		System.out.println("Json Data[0] Index Zero avatar Value: " + Avatar);
		System.out.println("Json Data[0] Index Zero firstName Value: " + firstName);

		Assert.assertEquals(lastName, "Bluth");
		Assert.assertEquals(Integer.parseInt(iD), 1);
		Assert.assertEquals(Avatar, "https://reqres.in/img/faces/1-image.jpg");
		Assert.assertEquals(firstName, "George");

		// d. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Header Array--> " + allHeaders);

	}

//	@Test
//	public void getAPITest() {
//		restClient = new RestClient();
//		restClient.get(url);

}