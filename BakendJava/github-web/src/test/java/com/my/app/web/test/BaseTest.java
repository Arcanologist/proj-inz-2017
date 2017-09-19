package com.my.app.web.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/resources/application-context.xml",
		"file:src/main/WebContent/WEB-INF/rest-dispatcher-servlet.xml" })
public abstract class BaseTest {

	private static final String SRC_MAIN_WEB_CONTENT = "src/main/WebContent";

	protected DispatcherServlet servlet;

	@Autowired
	protected ApplicationContext applicationContext;

	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;

	@Before
	public void initDispatcherServlet() throws Exception {
		servlet = new DispatcherServlet() {

			@Override
			protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent)
					throws BeansException {

				GenericWebApplicationContext gwac = new GenericWebApplicationContext();
				gwac.setParent(applicationContext);
				gwac.refresh();
				return gwac;
			}
		};

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		MockServletContext servletContext = new MockServletContext(SRC_MAIN_WEB_CONTENT,
				new FileSystemResourceLoader());

		servlet.init(new MockServletConfig(servletContext));
	}
}