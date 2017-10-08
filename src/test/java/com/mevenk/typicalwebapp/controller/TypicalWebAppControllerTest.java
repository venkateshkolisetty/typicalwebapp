/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.TAB_SPACE_AROUND_SINGLE_COLUN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.ModelMap;

import com.mevenk.typicalwebapp.service.ClientUtilService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Venkatesh
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TypicalWebAppController.class)
public class TypicalWebAppControllerTest {

	private Object returnedVal;

	private static final String TEST_REQUEST_RESPONSE_VALID_PARAM = "";
	private static final String TEST_REQUEST_RESPONSE_VALID_RESPONSE = "Response for calling testRequestResponse with parameter"
			+ TAB_SPACE_AROUND_SINGLE_COLUN + TEST_REQUEST_RESPONSE_VALID_PARAM;

	@InjectMocks
	TypicalWebAppController typicalWebAppController;

	@Spy
	TypicalWebAppController typicalWebAppControllerSpy;

	@Mock
	ClientUtilService clientUtilService;

	@Given("^Initiate TypicalWebApp Controller$")
	public void testRequestResponseCallMethod() {
		resetObjects();
	}

	@When("^Send TestRequestResponse Request with valis parameter$")
	public void testRequestResponseVerifyResponseSent() {
		doNothing().when(clientUtilService).logClientDetails(any(HttpServletRequest.class));
	}

	@Then("^Verify valid response received for TestRequestResponse$")
	public void testRequestResponseVerifyValidResponseSent() {
		returnedVal = typicalWebAppController.testRequestResponse(any(ModelMap.class), any(HttpServletRequest.class),
				TEST_REQUEST_RESPONSE_VALID_PARAM);
		verify(typicalWebAppController, times(1)).testRequestResponse(any(ModelMap.class),
				any(HttpServletRequest.class), TEST_REQUEST_RESPONSE_VALID_PARAM);
		assertEquals(returnedVal, TEST_REQUEST_RESPONSE_VALID_RESPONSE);
	}

	private void resetObjects() {
		typicalWebAppController = new TypicalWebAppController();
		typicalWebAppControllerSpy = spy(typicalWebAppController);
	}

}
