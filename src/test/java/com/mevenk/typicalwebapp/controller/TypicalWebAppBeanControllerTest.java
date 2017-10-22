/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mevenk.typicalwebapp.config.TypicalWebAppRootConfiguration;
import com.mevenk.typicalwebapp.config.TypicalWebAppServletConfiguration;

/**
 * @author Venkatesh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TypicalWebAppRootConfiguration.class, TypicalWebAppServletConfiguration.class })
public class TypicalWebAppBeanControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	{
		Properties properties = new Properties();
		properties.setProperty("typicalWebappPropertiesFileSource",
				"file:E:/work/temporary/TypicalWebapp/config/typicalwebapp.properties");
		properties.setProperty("typicalWebappLogsDirectoryLoaction", "E:/work/temporary/TypicalWebapp/Logs");
		properties.setProperty("typicalwebappUploadedFilesDirPath",
				"E:/work/temporary/TypicalWebapp/typicalwebappUploadedFiles");
		System.setProperties(properties);
	}

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webApplicationContext);
		this.mockMvc = builder.build();
	}

	@Test
	public void testUserController() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/typicalwebappbean")
				.param("typicalWebAppBeanId", "92757");
		this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
