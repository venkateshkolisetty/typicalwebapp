/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author VENKATESH
 *
 */
@Controller
public class TypicalWebAppController {

	private enum FileUploadStatus {
		SUCCESS, FAIl;
	}

	/**
	 * @param httpServletRequest
	 */
	private void logRequestDetails(HttpServletRequest httpServletRequest) {
		System.out.println(httpServletRequest.getRequestURL() + TypicalWebAppConstants.tabSpaceWithSingleColun
				+ httpServletRequest.getMethod() + TypicalWebAppConstants.tabSpaceWithDoubleColun + new Date());

		Map<String, String[]> requestParameterMap = httpServletRequest.getParameterMap();
		if (requestParameterMap.size() > 0) {

			System.out.println("Request Parameters");

			for (Entry<String, String[]> currentEntry : requestParameterMap.entrySet()) {
				System.out.println(TypicalWebAppConstants.tabSpace + currentEntry.getKey()
						+ TypicalWebAppConstants.tabSpaceWithSingleColun
						+ Arrays.toString(currentEntry.getValue()).replaceAll("^\\[|\\]$", ""));
			}

		}
	}

	/**
	 * @param httpServletRequest
	 */
	private void logClientDetails(HttpServletRequest httpServletRequest) {
		System.out.println(TypicalWebAppConstants.lineSeparator + "Client Details from Headers..."
				+ TypicalWebAppConstants.lineSeparator);
		Enumeration<?> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String paramName = (String) headerNames.nextElement();
			String paramValue = httpServletRequest.getHeader(paramName);
			System.out.println(paramName + TypicalWebAppConstants.tabSpaceWithDoubleColun + paramValue);
		}

		System.out.println(TypicalWebAppConstants.lineSeparator);

		System.out.println("Client Details from UserAgent utils..." + TypicalWebAppConstants.lineSeparator);

		UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));

		System.out.println("Client" + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ httpServletRequest.getRemoteHost() + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ userAgent.getId() + TypicalWebAppConstants.tabSpaceWithDoubleColun + userAgent.toString());
	}

	@RequestMapping(value = "*", method = RequestMethod.GET)
	public String applicationStartup(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(TypicalWebAppConstants.lineSeparator + "Application loaded at : " + new Date()
				+ TypicalWebAppConstants.tabSpaceWithDoubleColun + request.getRequestURL()
				+ TypicalWebAppConstants.lineSeparator);

		logRequestDetails(request);

		logClientDetails(request);

		System.out.println(TypicalWebAppConstants.lineSeparator + "Redirecting to Welcome Page...."
				+ TypicalWebAppConstants.lineSeparator);

		return "redirect:/welcome";

	}

	@RequestMapping(value = "testRequestResponse", method = RequestMethod.GET)
	public @ResponseBody String testRequestResponse(ModelMap model, HttpServletRequest request,
			@RequestParam(name = "testRequestResponseParameter1") String testRequestResponseParameter1) {
		logRequestDetails(request);
		return "Response for calling testRequestResponse with parameter"
				+ TypicalWebAppConstants.tabSpaceWithSingleColun + testRequestResponseParameter1;

	}

	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public String fileUpload(ModelMap modelMap, HttpServletRequest request) {
		logRequestDetails(request);
		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "welcome";

	}

	@RequestMapping(value = "fileUploadPage", method = RequestMethod.GET)
	public String fileUploadPage(ModelMap model, HttpServletRequest request) {
		logRequestDetails(request);
		model.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "fileUploadPage";

	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(ModelMap model, HttpServletRequest request,
			@RequestParam(name = "uploadedFile") MultipartFile uploadedFile) {

		logRequestDetails(request);

		FileUploadStatus fileUploadStatus = null;
		byte[] fileInBytes;

		try {
			String originalFilename = uploadedFile.getOriginalFilename();
			System.out.println("Rreceived File" + TypicalWebAppConstants.tabSpaceWithDoubleColun + originalFilename);
			fileInBytes = uploadedFile.getBytes();

			String destinationFilePath = "E:\\work\\temporary\\typicalwebappUploadedFiles"
					+ TypicalWebAppConstants.fileSeparator + originalFilename;

			// Path path = Paths.get(destinationFilePath);
			// Files.write(path, bytes);

			File destinationFile = new File(destinationFilePath);

			if (destinationFile.exists()) {
				System.out.println("Existing File deleted ? " + destinationFile.delete());
			}

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));
			bufferedOutputStream.write(fileInBytes);
			bufferedOutputStream.close();

			if (destinationFile.exists()) {
				fileUploadStatus = FileUploadStatus.SUCCESS;
			} else {
				fileUploadStatus = FileUploadStatus.FAIl;
			}

			System.out.println("Uploaded File Status" + TypicalWebAppConstants.tabSpaceWithDoubleColun
					+ fileUploadStatus.toString());

		} catch (Exception e) {
			e.printStackTrace();
			fileUploadStatus = FileUploadStatus.FAIl;
		}

		return fileUploadStatus.toString();

	}

}
