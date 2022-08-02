package com.qa.Unirest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class getRequestMethod {

	public static void main(String[] args) throws UnirestException {

		HttpResponse<JsonNode> jsonpResponse = Unirest.get("http://dummy.restapiexample.com/api/v1/employees").asJson();

		System.out.println("Status code : " + jsonpResponse.getStatus());
		System.out.println("Status Message : " + jsonpResponse.getStatusText());
		System.out.println("Status Body : " + jsonpResponse.getBody());

	}

}
