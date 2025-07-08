package com.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.controller.OfferRequest;
import com.springboot.controller.SegmentResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartOfferApplicationTests {


	@LocalServerPort
	private int port;

	@Test
	public void checkFlatXForOneSegment() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p1");
		OfferRequest offerRequest = new OfferRequest(1,"FLATX",10,segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result,true); // able to add offer
	}
	@Test
	public void checkFlatXPercent() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p2");
		OfferRequest offerRequest = new OfferRequest(1,"FLATPERCENT",10,segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result,true);
	}
	@Test
	public void checkFlatXPercentInvalidOfferType() throws Exception {
		List<String> segments = new ArrayList<>();
		segments.add("p2");
		OfferRequest offerRequest = new OfferRequest(1,"abc",10,segments);
		boolean result = addOffer(offerRequest);
		Assert.assertEquals(result,true);
	}

	public boolean addOffer(OfferRequest offerRequest) throws Exception {
		String urlString = "http://localhost:" + port + "/api/v1/offer";
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();

		String POST_PARAMS = mapper.writeValueAsString(offerRequest);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request did not work.");
		}
		return true;
	}
}
