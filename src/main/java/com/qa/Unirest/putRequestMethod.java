package com.qa.Unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class putRequestMethod {

	public static void main(String[] args) throws UnirestException {

		HttpResponse<JsonNode> jsonpResponse = Unirest.put("http://dummy.restapiexample.com/api/v1/update/id")
				.body("{\"name\":\"Sandio\",\"salary\":\"56753728\",\"age\":\"27\"}").asJson();

		System.out.println("Status code : " + jsonpResponse.getStatus());
		System.out.println("Status Message : " + jsonpResponse.getStatusText());
		System.out.println("Status Body : " + jsonpResponse.getBody());

	}

}
