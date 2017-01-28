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
		SUCCESS, FAIl;
	}

	FileUploadStatus uploadFile(MultipartFile uploadedFile);

	Object[] downloadFile(String fileToBeDownloaded);

}
