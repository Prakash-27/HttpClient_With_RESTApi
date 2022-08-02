package com.qa.java.net.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class deleteMethod {

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://dummy.restapiexample.com/api/v1/delete/created_id");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);

		int statusCode = connection.getResponseCode();
		System.out.println("Status code is --> " + statusCode);

		String responseMessage = connection.getResponseMessage();
		System.out.println("Response message --> " + responseMessage);

		InputStream inputStream = connection.getInputStream();

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		boolean line;
		StringBuffer stringBuffer = new StringBuffer();
		while (line = bufferedReader.readLine() != null) {
			stringBuffer.append(line);
		}
		System.out.println(stringBuffer);

	}

}