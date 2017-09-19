package com.my.app.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestDataReceiverImpl implements RestDataReciever {

	/**
	 * rest client, method return json as String by url parameter. This use GET web-method
	 * @see com.my.app.client.RestDataReciever#getDataFromService(java.lang.String)
	 */
	@Override
	public String getDataFromService(String url) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, String.class);
	}

}
