/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.CONFIG;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.lang.reflect.Field;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.mevenk.typicalwebapp.exception.InvalidPropertyException;

/**
 * @author Venkatesh
 *
 */
public class TypicalWebAppPropertiesLoader extends TypicalWebAppSourceBean {

	private static final Logger log = LogManager.getLogger(TypicalWebAppPropertiesLoader.class);

	public TypicalWebAppPropertiesLoader(String beanName) {
		super(beanName);
	}

	@Value("${correlationIdDateFormatPattern}")
	private String correlationIdDateFormatPattern;
	@Value("${timelyLoggerDateFormatPattern}")
	private String timelyLoggerDateFormatPattern;
	@Value("${sourceBeanDateFormatPattern}")
	private String sourceBeanDateFormatPattern;
	@Value("${miscDateFormatPattern}")
	private String miscDateFormatPattern;

	@Value("${datePoolingCronExpression}")
	private String datePoolingCronExpression;

	@Value("${typicalWebAppBeansDataSourceLocation}")
	private String typicalWebAppBeansDataSourceLocation;
	@Value("${typicalwebappUploadedFilesDirPath}")
	private String typicalwebappUploadedFilesDirPath;

	@PostConstruct
	public void loadAndSetProperties() throws InvalidPropertyException, IllegalAccessException {
		verifyIfAnyPropertyIsInvalid();
	}

	private void verifyIfAnyPropertyIsInvalid() throws InvalidPropertyException, IllegalAccessException {
		String currentProperty = null;
		String currentPropertyValue = null;
		Field[] fields = TypicalWebAppPropertiesLoader.class.getDeclaredFields();
		for (Field fieldCurrent : fields) {
			if (fieldCurrent.getAnnotation(Value.class) != null) {
				fieldCurrent.setAccessible(true);
				currentProperty = fieldCurrent.getName();
				currentPropertyValue = (String) fieldCurrent.get(this);
				if (isEmpty(currentPropertyValue) || isBlank(currentPropertyValue)) {
					throw new InvalidPropertyException(currentProperty);
				}
				log.log(CONFIG, "Property[{}] loaded with value: {}", currentProperty, currentPropertyValue);
			}
		}
	}

	/**
	 * @return the correlationIdDateFormatPattern
	 */
	public String getCorrelationIdDateFormatPattern() {
		return correlationIdDateFormatPattern;
	}

	/**
	 * @return the timelyLoggerDateFormatPattern
	 */
	public String getTimelyLoggerDateFormatPattern() {
		return timelyLoggerDateFormatPattern;
	}

	/**
	 * @return the sourceBeanDateFormatPattern
	 */
	public String getSourceBeanDateFormatPattern() {
		return sourceBeanDateFormatPattern;
	}

	/**
	 * @return the miscDateFormatPattern
	 */
	public String getMiscDateFormatPattern() {
		return miscDateFormatPattern;
	}

	/**
	 * @return the datePoolingCronExpression
	 */
	public String getDatePoolingCronExpression() {
		return datePoolingCronExpression;
	}

	/**
	 * @return the typicalWebAppBeansDataSourceLocation
	 */
	public String getTypicalWebAppBeansDataSourceLocation() {
		return typicalWebAppBeansDataSourceLocation;
	}

	/**
	 * @return the typicalwebappUploadedFilesDirPath
	 */
	public String getTypicalwebappUploadedFilesDirPath() {
		return typicalwebappUploadedFilesDirPath;
	}

}
