package com.qa.Unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class deleteRequestMethod {

	public static void main(String[] args) throws UnirestException {

		HttpResponse<JsonNode> jsonpResponse = Unirest.delete("http://dummy.restapiexample.com/api/v1/delete/id").asJson();

		System.out.println("Status code : " + jsonpResponse.getStatus());
		System.out.println("Status Message : " + jsonpResponse.getStatusText());
		System.out.println("Status Body : " + jsonpResponse.getBody());
		
		
	}

}
