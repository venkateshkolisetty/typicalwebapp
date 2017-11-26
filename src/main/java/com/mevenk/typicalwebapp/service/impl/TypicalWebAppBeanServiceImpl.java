/**
 * 
 */
package com.mevenk.typicalwebapp.service.impl;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.gsonTypicalWebAppBean;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.EXT_JSON;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.FILE_SEPARATOR;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.exceptionStactTraceAsString;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomPastDate;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomPositiveNumber;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.deleteIfExists;
import static org.apache.commons.io.FileUtils.writeStringToFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;
import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;
import com.mevenk.typicalwebapp.config.TypicalWebAppSourceBean;
import com.mevenk.typicalwebapp.service.TypicalWebAppBeanService;

/**
 * @author Venkatesh
 *
 */
public class TypicalWebAppBeanServiceImpl extends TypicalWebAppSourceBean implements TypicalWebAppBeanService {

	private static final Logger log = LogManager.getLogger(TypicalWebAppBeanServiceImpl.class);

	@Autowired
	private TypicalWebAppPropertiesLoader typicalWebAppPropertiesLoader;

	public TypicalWebAppBeanServiceImpl(String beanName) {
		super(beanName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mevenk.typicalwebapp.service.TypicalWebAppBeanService#
	 * isTypicalWebAppBeanAvailable(int)
	 */
	@Override
	public boolean isTypicalWebAppBeanAvailable(int typicalWebAppBeanId) {
		return fileTypicalWebAppBean(typicalWebAppBeanId).exists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mevenk.typicalwebapp.service.TypicalWebAppBeanService#
	 * addTypicalWebAppBean()
	 */
	@Override
	public TypicalWebAppBean addTypicalWebAppBean() {
		TypicalWebAppBean typicalWebAppBean = new TypicalWebAppBean(randomString(50), randomPositiveNumber(9),
				randomPastDate());
		File fileTypicalWebAppBean = fileTypicalWebAppBean(typicalWebAppBean.getTypicalWebAppBeanId());
		log.debug("{} already exists?: {}", typicalWebAppBean.getTypicalWebAppBeanId(), fileTypicalWebAppBean.exists());
		try {
			writeTypicalWebAppBeanToFile(typicalWebAppBean, fileTypicalWebAppBean);
			log.info("TypicalWebAppBean created with id: {}", typicalWebAppBean.getTypicalWebAppBeanId());
			return typicalWebAppBean;
		} catch (IOException exception) {
			log.error(exceptionStactTraceAsString(exception));
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mevenk.typicalwebapp.service.TypicalWebAppBeanService#
	 * getTypicalWebAppBean(int)
	 */
	@Override
	public TypicalWebAppBean getTypicalWebAppBean(int typicalWebAppBeanId) {
		TypicalWebAppBean typicalWebAppBean = null;
		try {
			typicalWebAppBean = typicalWebAppBeanFromJson(typicalWebAppBeanId);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException exception) {
			log.error(exceptionStactTraceAsString(exception));
		}
		return typicalWebAppBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mevenk.typicalwebapp.service.TypicalWebAppBeanService#
	 * modifyTypicalWebAppBean(com.mevenk.typicalwebapp.bean.TypicalWebAppBean)
	 */
	@Override
	public TypicalWebAppBean modifyTypicalWebAppBean(TypicalWebAppBean typicalWebAppBean) {
		TypicalWebAppBean typicalWebAppBeanFromJson = null;
		File fileTypicalWebAppBeanFromJson = fileTypicalWebAppBean(typicalWebAppBean.getTypicalWebAppBeanId());
		if (!fileTypicalWebAppBeanFromJson.exists()) {
			return null;
		}
		try {
			typicalWebAppBeanFromJson = typicalWebAppBeanFromJson(fileTypicalWebAppBeanFromJson);

			typicalWebAppBeanFromJson.setRandomString(typicalWebAppBean.getRandomString());
			typicalWebAppBeanFromJson.setRandomInteger(typicalWebAppBean.getRandomInteger());
			typicalWebAppBeanFromJson.setRandomDate(typicalWebAppBean.getRandomDate());

			writeTypicalWebAppBeanToFile(typicalWebAppBeanFromJson, fileTypicalWebAppBeanFromJson);

		} catch (JsonSyntaxException | JsonIOException | IOException exception) {
			log.error(exceptionStactTraceAsString(exception));
		}
		return typicalWebAppBeanFromJson;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mevenk.typicalwebapp.service.TypicalWebAppBeanService#
	 * deleteTypicalWebAppBean(int)
	 */
	@Override
	public boolean deleteTypicalWebAppBean(int typicalWebAppBeanId) {
		try {
			boolean isFileDeleted = deleteIfExists(fileTypicalWebAppBean(typicalWebAppBeanId).toPath());
			if (isFileDeleted) {
				return true;
			} else {
				log.error("Error deleting {}. File does not exist!", typicalWebAppBeanId);
				return false;
			}
		} catch (IOException ioException) {
			log.error("Error deleting {}", typicalWebAppBeanId);
			log.error(exceptionStactTraceAsString(ioException));
			return false;
		}

	}

	private File fileTypicalWebAppBean(int typicalWebAppBeanId) {
		return new File(typicalWebAppPropertiesLoader.getTypicalWebAppBeansDataSourceLocation() + FILE_SEPARATOR
				+ typicalWebAppBeanId + EXT_JSON);
	}

	private TypicalWebAppBean typicalWebAppBeanFromJson(int typicalWebAppBeanId) throws FileNotFoundException {
		return gsonTypicalWebAppBean.fromJson(new FileReader(fileTypicalWebAppBean(typicalWebAppBeanId)),
				TypicalWebAppBean.class);
	}

	private TypicalWebAppBean typicalWebAppBeanFromJson(File fileTypicalWebAppBean) throws FileNotFoundException {
		return gsonTypicalWebAppBean.fromJson(new FileReader(fileTypicalWebAppBean), TypicalWebAppBean.class);
	}

	/**
	 * @param typicalWebAppBean
	 * @param fileTypicalWebAppBean
	 * @throws IOException
	 */
	private void writeTypicalWebAppBeanToFile(TypicalWebAppBean typicalWebAppBean, File fileTypicalWebAppBean)
			throws IOException {
		writeStringToFile(fileTypicalWebAppBean, gsonTypicalWebAppBean.toJson(typicalWebAppBean), UTF_8, false);
	}

}
