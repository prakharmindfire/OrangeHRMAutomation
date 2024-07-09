package com.qa.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.qa.base.TestBase;

public class APIUtil extends TestBase {
	public static String getDocIdByOrgIdMrnAcc(String hostURL, String orgID, String mrn, String acc) throws IOException {
		String responseBody = null;
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			// HTTP GET method
			HttpGet httpget = new HttpGet(hostURL+"/services/organization/"+orgID+"/episode/document/mrn/"+mrn+"/accountNumber/"+acc);
			
			System.out.println("Executing request " + httpget.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
		}
		return responseBody;
	}
	
	public String sendHL7RequestToADT(String hl7docCreatingUrl, String HL7Body) throws IOException {
		String responseBody = null;
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			// HTTP GET method
			HttpPut httpPut = new HttpPut(hl7docCreatingUrl);
			httpPut.setHeader("Content-type", "text / plain");
			StringEntity stringEntity = new StringEntity(HL7Body);
			httpPut.setEntity(stringEntity);
			
			System.out.println("Executing request " + httpPut.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			responseBody = httpclient.execute(httpPut, responseHandler);
			System.out.println("----------------------------------------");
			System.out.println(responseBody);
		}
		return responseBody;
	}

	public static String updateAndInvalidateEmail(String hostUrl, String emailId, String userId) throws IOException {
		String responseBody = null;
		String requestUrl=hostUrl+"/transitionsecurityservice/services/v1/users/"+userId+"/email";
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			// HTTP PUT method
			HttpPut httpPut = new HttpPut(requestUrl);
			httpPut.setHeader("Content-type", "application/json");
			StringEntity stringEntity = new StringEntity(emailId);
			httpPut.setEntity(stringEntity);

			reportLog("Executing request " + httpPut.getRequestLine());

			// Create a custom response handler
			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			responseBody = httpclient.execute(httpPut, responseHandler);
			reportLog(responseBody);
		}
		return responseBody;
	}
}
