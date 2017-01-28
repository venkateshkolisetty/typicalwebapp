/**
 * 
 */
package com.mevenk.typicalwebapp.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.typicalwebapp.service.TypicalWebAppService;
import com.mevenk.typicalwebapp.util.TypicalWebAppConstants;

/**
 * @author VENKATESH
 *
 */
@Component
public class TypicalWebAppServiceImpl implements TypicalWebAppService {

	String filesDirPath = "E:\\work\\temporary\\typicalwebappUploadedFiles" + TypicalWebAppConstants.fileSeparator;

	@Override
	public FileUploadStatus uploadFile(MultipartFile uploadedFile) {
		FileUploadStatus fileUploadStatus = null;

		try {

			String originalFilename = uploadedFile.getOriginalFilename();
			System.out.println("Rreceived File" + TypicalWebAppConstants.tabSpaceWithDoubleColun + originalFilename);
			byte[] fileInBytes = uploadedFile.getBytes();

			// Path path = Paths.get(filesDirPath + originalFilename);
			// Files.write(path, bytes);

			File destinationFile = new File(filesDirPath + originalFilename);

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

		} catch (IOException exception) {
			fileUploadStatus = FileUploadStatus.FAIl;
			exception.printStackTrace();
		}

		System.out.println(
				"Uploaded File Status" + TypicalWebAppConstants.tabSpaceWithDoubleColun + fileUploadStatus.toString());

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

			System.out.println(filetoconverttobytes.getAbsolutePath() + " Exists ? " + filetoconverttobytes.exists());

			fileDataInBytes = new byte[(int) filetoconverttobytes.length()];
			fileinputstream = new FileInputStream(filetoconverttobytes);
			fileinputstream.read(fileDataInBytes);
			fileinputstream.close();

			Object[] returnObject = { fileDataInBytes, fileName };
			return returnObject;

		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
