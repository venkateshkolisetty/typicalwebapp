/**
 * 
 */
package com.mevenk.typicalwebapp.test.config;

import static org.testng.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mevenk.typicalwebapp.config.TypicalWebAppRootConfiguration;
import com.mevenk.typicalwebapp.config.TypicalWebAppServletConfiguration;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;

/**
 * @author Venkatesh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TypicalWebAppRootConfiguration.class, TypicalWebAppServletConfiguration.class })
public class TypicalWebAppTestInitializer {

	private static final String TYPICAL_WEB_APP_PROPERTIES_FILE_SOURCE = "file:E:/work/temporary/TypicalWebapp/config/typicalwebapp.properties";
	private static final String TEST_FILES_UPLOAD_DIR = "tests/resources/filesUploadDir";
	private static final String TEST_LOGS_DIR = "tests/testLogs";

	static {
		System.setProperty("typicalWebappPropertiesFileSource", TYPICAL_WEB_APP_PROPERTIES_FILE_SOURCE);
		System.setProperty("typicalwebappUploadedFilesDirPath", TEST_FILES_UPLOAD_DIR);
		System.setProperty("typicalWebappLogsDirectoryLoaction", TEST_LOGS_DIR);
	}

	@Autowired
	TypicalWebAppService typicalWebAppService;

	@Test
	public void testContext() {
		assertNotNull(typicalWebAppService);
	}

}
