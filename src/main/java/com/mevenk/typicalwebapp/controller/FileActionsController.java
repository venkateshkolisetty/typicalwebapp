/**
 * 
 */
package com.mevenk.typicalwebapp.controller;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.THREAD_CONTEXT_KEY;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.exception.DownloadFileNotFoundException;
import com.mevenk.typicalwebapp.service.ClientUtilService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.service.TypicalWebAppService.FileUploadStatus;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;
import com.mevenk.typicalwebapp.util.TypicalWebAppUtil;

/**
 * @author VENKATESH
 *
 */
@Controller()
@RequestMapping
public class FileActionsController {

	private static final Logger log = LogManager.getLogger(FileActionsController.class);

	private static final String LINE_SEPARATOR = TypicalWebAppConstants.lineSeparator;

	@Autowired
	TypicalWebAppService typicalWebAppService;

	@Autowired
	ClientUtilService clientUtilService;

	@RequestMapping(value = "fileActionsPage", method = RequestMethod.GET)
	public String fileActionsPage(ModelMap modelMap, HttpServletRequest httpServletRequest) {

		String sessionId = httpServletRequest.getSession().getId();

		ThreadContext.put(THREAD_CONTEXT_KEY, "fileActionsPage#" + sessionId);

		log.trace("Session Id : " + sessionId);

		sessionId = null;

		log.debug(LINE_SEPARATOR + "Page called at : " + new Date() + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ httpServletRequest.getRequestURL() + LINE_SEPARATOR);

		clientUtilService.logRequestDetails(httpServletRequest);

		modelMap.addAttribute("greeting", "Hello, Welcome to Spring Project");
		return "fileActionsPage";

	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(ModelMap modelMap, HttpServletRequest httpServletRequest,
			@RequestParam(name = "uploadedFile") MultipartFile uploadedFile) {

		ThreadContext.put(THREAD_CONTEXT_KEY, "fileUpload#" + uploadedFile.getOriginalFilename()
				+ TypicalWebAppUtil.dateForCorrelationId(new Date()));

		log.debug(LINE_SEPARATOR + "Called at : " + new Date() + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ httpServletRequest.getRequestURL() + LINE_SEPARATOR);

		clientUtilService.logRequestDetails(httpServletRequest);

		FileUploadStatus fileUploadStatus = null;

		try {
			log.info("Received File Name : " + uploadedFile.getOriginalFilename());

			fileUploadStatus = typicalWebAppService.uploadFile(uploadedFile);

		} catch (Exception exception) {
			log.fatal(TypicalWebAppUtil.exceptionStactTraceAsString(exception));
			fileUploadStatus = FileUploadStatus.FAIl;
		}

		return fileUploadStatus.toString();

	}

	@RequestMapping(value = "fileDownload", method = RequestMethod.GET)
	public void fileDownload(ModelMap model, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(name = "fileToBeDownloaded", required = false) String fileToBeDownloaded) {

		ThreadContext.put(THREAD_CONTEXT_KEY,
				"fileDownload#" + fileToBeDownloaded + TypicalWebAppUtil.dateForCorrelationId(new Date()));

		log.debug(LINE_SEPARATOR + "Called at : " + new Date() + TypicalWebAppConstants.tabSpaceWithDoubleColun
				+ httpServletRequest.getRequestURL() + LINE_SEPARATOR);

		Object[] dataObject;

		byte[] selectedfiledatainbytes;
		String fileName;

		try {

			log.info("Received File Name : " + fileToBeDownloaded);

			dataObject = typicalWebAppService.downloadFile(fileToBeDownloaded);

			selectedfiledatainbytes = (byte[]) dataObject[0];
			fileName = (String) dataObject[1];

			if (selectedfiledatainbytes == null) {
				log.fatal("Bytes received NULL");
				throw new DownloadFileNotFoundException(fileToBeDownloaded + " - File Not Found");
			}

			httpServletResponse.reset();
			httpServletResponse.addHeader("Content-Disposition", "attachment; filename = " + fileName);
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
			log.fatal(TypicalWebAppUtil.exceptionStactTraceAsString(exception));
			try {
				String sendErrorMessage = exception instanceof DownloadFileNotFoundException ? exception.getMessage()
						: "Error Downloading File!";
				httpServletResponse.sendError(404, sendErrorMessage);
			} catch (Exception exceptionResponse) {
				log.error("Response sending Error!!");
				log.fatal(TypicalWebAppUtil.exceptionStactTraceAsString(exceptionResponse));
			}

		}

	}

}
