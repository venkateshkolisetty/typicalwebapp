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

	@Autowired
	TypicalWebAppService typicalWebAppService;

	@Test
	public void testContext() {
		assertNotNull(typicalWebAppService);
	}

}
