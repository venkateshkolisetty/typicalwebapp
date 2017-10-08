/**
 * 
 */
package com.mevenk.typicalwebapp.testrunner;

import static com.mevenk.typicalwebapp.testrunner.TypicalWebAppTestRunner.FEATURES_BASE_DIR;
import static com.mevenk.typicalwebapp.testrunner.TypicalWebAppTestRunner.PLUGIN_HTML;
import static com.mevenk.typicalwebapp.testrunner.TypicalWebAppTestRunner.PLUGIN_JSON;
import static com.mevenk.typicalwebapp.testrunner.TypicalWebAppTestRunner.PLUGIN_PRETTY;
import static com.mevenk.typicalwebapp.testrunner.TypicalWebAppTestRunner.TYPICALWEBAPP_CONTROLLER_FEATURE;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Venkatesh
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = FEATURES_BASE_DIR + TYPICALWEBAPP_CONTROLLER_FEATURE, plugin = { PLUGIN_PRETTY, PLUGIN_HTML,
		PLUGIN_JSON })
public class TypicalWebAppControllerTestRunner extends TypicalWebAppTestRunner {

	@BeforeClass
	public static void initializePrerequisitesForTest() {
		initializePrerequisites();
	}

}
