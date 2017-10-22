/**
 * 
 */
package com.mevenk.typicalwebapp.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author VENKATESH
 *
 */
public interface TypicalWebAppService {

	enum FileUploadStatus {
		FILE_UPLOAD_SUCCESS, FILE_UPLOAD_FAIl;
	}

	FileUploadStatus uploadFile(MultipartFile uploadedFile);

	Object[] downloadFile(String fileToBeDownloaded);
	
}
