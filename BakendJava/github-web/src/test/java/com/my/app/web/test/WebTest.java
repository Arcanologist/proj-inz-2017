package com.my.app.web.test;

import java.io.IOException;

import javax.servlet.ServletException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class WebTest extends BaseTest {

	private static final String CREATED_AT = "createdAt";
	private static final String STARS = "stars";
	private static final String CLONE_URL = "cloneUrl";
	private static final String DESCRIPTION = "description";
	private static final String FULL_NAME = "fullName";
	private static final String POST = "POST";
	private static final String GET = "GET";
	private static final String EXCEPTION_MESSAGE = "exception_message";
	private static final String ERROR_CODE = "error_code";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String ACCEPT = "Accept";
	private static final String APPLICATION_XML = "application/xml";
	private static final String APPLICATION_JSON = "application/json";

	@Test
	public void testGetMetadataSuccess() throws Exception {
		createRequest(GET, "Adrian-java", "PRI2");
		Assert.assertEquals(200, response.getStatus());
		String result = response.getContentAsString();
		
		Assert.assertThat(result, CoreMatchers.containsString(FULL_NAME));
		Assert.assertThat(result, CoreMatchers.containsString(DESCRIPTION));
		Assert.assertThat(result, CoreMatchers.containsString(CLONE_URL));
		Assert.assertThat(result, CoreMatchers.containsString(STARS));
		Assert.assertThat(result, CoreMatchers.containsString(CREATED_AT));
		
		Assert.assertThat(result, RegMatcher.matchesRegex(".*\"[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}\".*"));
		
	}

	@Test
	public void testGetMetadataReturnErrorMessage() throws Exception {
		createRequest(GET, "abc", "def");
		String result = response.getContentAsString();
		Assert.assertEquals(500, response.getStatus());
		Assert.assertThat(result, CoreMatchers.containsString(ERROR_CODE));
		Assert.assertThat(result, CoreMatchers.containsString(EXCEPTION_MESSAGE));
	}

	@Test
	public void testGetMetadataMethodNotAllowed() throws Exception {
		createRequest(POST, "abc", "def");
		Assert.assertEquals(405, response.getStatus());
	}

	private void createRequest(String method, String owner, String repo) throws ServletException,
			IOException {
		request.setMethod(method);
		request.addHeader(ACCEPT, APPLICATION_JSON);
		request.addHeader(CONTENT_TYPE, APPLICATION_XML);
		request.setRequestURI("/repositories/" + owner + "/" + repo);
		request.setContentType(APPLICATION_JSON);

		servlet.service(request, response);
	}
}
