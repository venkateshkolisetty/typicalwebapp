/**
 * 
 */
package com.mevenk.typicalwebapp.testrunner;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.APP_BASE_DIR;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.FILE_SEPARATOR;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppTestRunner {

	private static final String TESTS_DIR = "tests";
	protected static final String TESTS_LOGS_DIR = TESTS_DIR + FILE_SEPARATOR + "testLogs";
	private static final String RESOURCES_BASE_DIR = "tests/resources";
	private static final String REPORTS_BASE_DIR = "tests/reports";

	protected static final String FEATURES_BASE_DIR = "tests/resources/features/";
	protected static final String PLUGIN_PRETTY = "pretty";
	protected static final String PLUGIN_HTML = "html:tests/reports/cucumber-html-report";
	protected static final String PLUGIN_JSON = "json:tests/reports/cucumber-report.json";

	/* FEATURES */

	protected static final String TYPICALWEBAPP_CONTROLLER_FEATURE = "TypicalWebAppController.feature";

	/* FEATURES - END */

	protected static void initializePrerequisites() {
		System.setProperty("typicalWebappLogsDirectoryLoaction", APP_BASE_DIR + FILE_SEPARATOR + TESTS_LOGS_DIR);
	}

}
