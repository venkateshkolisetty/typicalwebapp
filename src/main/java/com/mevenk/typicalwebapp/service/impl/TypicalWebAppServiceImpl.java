/**
 * 
 */
package com.mevenk.typicalwebapp.service.impl;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.FILE_SEPARATOR;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;
import com.mevenk.typicalwebapp.config.TypicalWebAppSourceBean;
import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;
import com.mevenk.typicalwebapp.util.TypicalWebAppUtil;

/**
 * @author VENKATESH
 *
 */
public class TypicalWebAppServiceImpl extends TypicalWebAppSourceBean implements TypicalWebAppService {

	private static final Logger log = LogManager.getLogger(TypicalWebAppServiceImpl.class);

	@Autowired
	private TypicalWebAppPropertiesLoader typicalWebAppPropertiesLoader;

	String filesDirPath;

	public TypicalWebAppServiceImpl(String beanName) {
		super(beanName);
	}

	@PostConstruct
	public void setup() {
		filesDirPath = typicalWebAppPropertiesLoader.getTypicalwebappUploadedFilesDirPath() + FILE_SEPARATOR;
	}

	@Override
	public FileUploadStatus uploadFile(MultipartFile uploadedFile) {
		FileUploadStatus fileUploadStatus = null;

		try {

			String originalFilename = uploadedFile.getOriginalFilename();
			log.info("Received File {}{}", TypicalWebAppConstants.SPACE_AROUND_DOUBLE_COLUN, originalFilename);
			byte[] fileInBytes = uploadedFile.getBytes();

			// Path path = Paths.get(filesDirPath + originalFilename);
			// Files.write(path, bytes);

			File destinationFile = new File(filesDirPath + originalFilename);

			if (destinationFile.exists()) {
				log.debug("Existing File deleted ? {}", destinationFile.delete());
			}

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destinationFile));

			bufferedOutputStream.write(fileInBytes);

			bufferedOutputStream.close();

			if (destinationFile.exists()) {
				fileUploadStatus = FileUploadStatus.FILE_UPLOAD_SUCCESS;
			} else {
				fileUploadStatus = FileUploadStatus.FILE_UPLOAD_FAIl;
			}

		} catch (IOException exception) {
			fileUploadStatus = FileUploadStatus.FILE_UPLOAD_FAIl;
			log.error("{}", TypicalWebAppUtil.exceptionStactTraceAsString(exception));
		}

		log.info("Uploaded File Status {}{}", TypicalWebAppConstants.SPACE_AROUND_DOUBLE_COLUN,
				fileUploadStatus.toString());

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
			log.debug("{} Exists ? {}", filetoconverttobytes.getAbsolutePath(), fileExists);

			if (fileExists) {

				fileDataInBytes = new byte[(int) filetoconverttobytes.length()];
				fileinputstream = new FileInputStream(filetoconverttobytes);
				fileinputstream.read(fileDataInBytes);
				fileinputstream.close();
			} else {
				log.error("{} not available !!", filetoconverttobytes);
			}

			Object[] returnObject = { fileDataInBytes, fileName };
			return returnObject;

		} catch (Exception exception) {
			log.error("{}", TypicalWebAppUtil.exceptionStactTraceAsString(exception));
			return null;
		}
	}

}
