/**
 * 
 */
package com.mevenk.typicalwebapp.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;
import com.mevenk.typicalwebapp.util.TypicalWebAppUtil;

/**
 * @author VENKATESH
 *
 */
@Component
public class TypicalWebAppServiceImpl implements TypicalWebAppService {

	private static final Logger log = LogManager.getLogger(TypicalWebAppServiceImpl.class);

	private static final String LINE_SEPARATOR = TypicalWebAppConstants.lineSeparator;
	private static final String FILE_SEPARATOR = TypicalWebAppConstants.fileSeparator;

	String filesDirPathFromSystemProperty = System.getProperty("typicalwebappUploadedFilesDirPath");
	String filesDirPath = filesDirPathFromSystemProperty + FILE_SEPARATOR;

	@Override
	public FileUploadStatus uploadFile(MultipartFile uploadedFile) {
		FileUploadStatus fileUploadStatus = null;

		try {

			String originalFilename = uploadedFile.getOriginalFilename();
			log.info("Received File" + TypicalWebAppConstants.tabSpaceWithDoubleColun + originalFilename);
			byte[] fileInBytes = uploadedFile.getBytes();

			// Path path = Paths.get(filesDirPath + originalFilename);
			// Files.write(path, bytes);

			File destinationFile = new File(filesDirPath + originalFilename);

			if (destinationFile.exists()) {
				log.debug("Existing File deleted ? " + destinationFile.delete());
			}

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));

			bufferedOutputStream.write(fileInBytes);

			bufferedOutputStream.close();

			if (destinationFile.exists()) {
				fileUploadStatus = FileUploadStatus.SUCCESS;
			} else {
				fileUploadStatus = FileUploadStatus.FAIl;
			}

		} catch (IOException exception) {
			fileUploadStatus = FileUploadStatus.FAIl;
			log.fatal(TypicalWebAppUtil.exceptionStactTraceAsString(exception));
		}

		log.info("Uploaded File Status" + TypicalWebAppConstants.tabSpaceWithDoubleColun + fileUploadStatus.toString());

		return fileUploadStatus;

	}

	@Override
	public Object[] downloadFile(String fileToBeDownloaded) {

		byte[] fileDataInBytes = null;
		String fileName = null;

		FileInputStream fileinputstream = null;

		try {

			String fileNameToBeDownloaded = fileToBeDownloaded;

			File filetoconverttobytes = new File(filesDirPath + fileNameToBeDownloaded);

			boolean fileExists = filetoconverttobytes.exists();
			log.debug(filetoconverttobytes.getAbsolutePath() + " Exists ? " + fileExists);

			if (fileExists) {

				fileDataInBytes = new byte[(int) filetoconverttobytes.length()];
				fileinputstream = new FileInputStream(filetoconverttobytes);
				fileinputstream.read(fileDataInBytes);
				fileinputstream.close();
			} else {
				log.error("File not available!");
				log.fatal(filetoconverttobytes + " not available !!");
			}

			Object[] returnObject = { fileDataInBytes, fileName };
			return returnObject;

		} catch (Exception exception) {
			log.fatal(TypicalWebAppUtil.exceptionStactTraceAsString(exception));
			return null;
		}
	}

}
