/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService.FileUploadStatus;

/**
 * @author VENKATESH
 *
 */
@Controller()
@RequestMapping(value = "fileActions")
public class FileActionsController {

	@Autowired
	TypicalWebAppService typicalWebAppService;

	@Autowired
	ClientUtilService clientUtilService;

	@RequestMapping(value = "fileActionsPage", method = RequestMethod.GET)
	public String fileActionsPage(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		clientUtilService.logRequestDetails(httpServletRequest);
		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "fileActionsPage";

	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "uploadedFile") MultipartFile uploadedFile) {

		clientUtilService.logRequestDetails(httpServletRequest);

		FileUploadStatus fileUploadStatus = null;

		try {

			fileUploadStatus = typicalWebAppService.uploadFile(uploadedFile);

		} catch (Exception e) {
			e.printStackTrace();
			fileUploadStatus = FileUploadStatus.FAIl;
		}

		return fileUploadStatus.toString();

	}

	@RequestMapping(value = "fileDownload", method = RequestMethod.GET)
	public void fileDownload(ModelMap model, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "fileToBeDownloaded", required = false) String fileToBeDownloaded) {

		Object[] dataObject;

		byte[] selectedfiledatainbytes;
		String fileNane;

		try {

			dataObject = typicalWebAppService.downloadFile(fileToBeDownloaded);

			selectedfiledatainbytes = (byte[]) dataObject[0];
			fileNane = (String) dataObject[1];

			httpServletResponse.reset();
			httpServletResponse.addHeader("Content-Disposition", "attachment; filename = " + fileNane);
			httpServletResponse.setContentLength(selectedfiledatainbytes.length);
			// httpServletResponse.setContentType("");

			Cookie fileDownloadCookie = new Cookie("fileDownload", "true");
			fileDownloadCookie.setPath("/");

			httpServletResponse.addCookie(fileDownloadCookie);

			OutputStream outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
			outputStream.write(selectedfiledatainbytes);
			outputStream.flush();
			outputStream.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
