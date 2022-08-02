package com.qa.Unirest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class postRequestMethod {

	public static void main(String[] args) throws UnirestException {

		HttpResponse<JsonNode> jsonpResponse = Unirest.post("http://dummy.restapiexample.com/api/v1/create")
				.body("{\"name\":\"Sandio\",\"salary\":\"56753728\",\"age\":\"30\"}").asJson();

		System.out.println("Status code : " + jsonpResponse.getStatus());
		System.out.println("Status Message : " + jsonpResponse.getStatusText());
		System.out.println("Status Body : " + jsonpResponse.getBody());

	}

}
