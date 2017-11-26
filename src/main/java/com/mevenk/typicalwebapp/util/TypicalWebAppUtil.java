/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ANGLE_BRACKET_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ANGLE_BRACKET_OPEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.COMMA_AND_SPACE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.EMPTY_STRING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.POUND_SIGN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_OPEN;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;
import com.mevenk.typicalwebapp.config.TypicalWebAppSourceBean;

/**
 * @author Venkatesh
 *
 */
public class TypicalWebAppUtil extends TypicalWebAppSourceBean {

	@Autowired
	private TypicalWebAppPropertiesLoader typicalWebAppPropertiesLoader;

	private static final Class<?>[] baseClasses = { String.class, Byte.class, Short.class, Integer.class, Long.class,
			Float.class, Double.class, Boolean.class };

	private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private static final int INT_MAX_VALUE_LENGTH = String.valueOf(Integer.MAX_VALUE).length();

	private static SimpleDateFormat SIMPLE_DATE_FORMAT_MISC_DATE;

	private static SimpleDateFormat SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER;

	public static SimpleDateFormat SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES;

	public static SimpleDateFormat SIMPLE_DATE_FORMAT_CORRELATION_ID;

	public TypicalWebAppUtil(String beanName) {
		super(beanName);
	}

	@PostConstruct
	public void setProperties() {
		SIMPLE_DATE_FORMAT_MISC_DATE = new SimpleDateFormat(typicalWebAppPropertiesLoader.getMiscDateFormatPattern());
		SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER = new SimpleDateFormat(
				typicalWebAppPropertiesLoader.getTimelyLoggerDateFormatPattern());
		SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES = new SimpleDateFormat(
				typicalWebAppPropertiesLoader.getSourceBeanDateFormatPattern());
		SIMPLE_DATE_FORMAT_CORRELATION_ID = new SimpleDateFormat(
				typicalWebAppPropertiesLoader.getCorrelationIdDateFormatPattern());
	}

	public static synchronized String randomUUIDString() {
		return UUID.randomUUID().toString();
	}

	public static String formatDateToString(Date date) {
		return SIMPLE_DATE_FORMAT_MISC_DATE.format(date);
	}

	public static String currentDateForTimelyDateLogger() {
		return SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER.format(new Date());
	}

	public static synchronized Date randomDate() {
		return randomFutureDate();
	}

	public static synchronized Date randomPastDate() {
		Random randomCalendar = new Random();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, currentYear - randomCalendar.nextInt(10));
		calendar.set(Calendar.MONTH, currentYear - randomCalendar.nextInt(11));
		calendar.set(Calendar.DAY_OF_MONTH, currentYear - randomCalendar.nextInt(26));
		calendar.set(Calendar.HOUR, currentYear - randomCalendar.nextInt(11));
		calendar.set(Calendar.MINUTE, currentYear - randomCalendar.nextInt(59));
		calendar.set(Calendar.SECOND, currentYear - randomCalendar.nextInt(59));
		calendar.set(Calendar.MILLISECOND, currentYear - randomCalendar.nextInt(998));
		return calendar.getTime();
	}

	public static synchronized Date randomFutureDate() {
		Random randomCalendar = new Random();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, currentYear + randomCalendar.nextInt(10));
		calendar.set(Calendar.MONTH, currentYear + randomCalendar.nextInt(11));
		calendar.set(Calendar.DAY_OF_MONTH, currentYear + randomCalendar.nextInt(26));
		calendar.set(Calendar.HOUR, currentYear + randomCalendar.nextInt(11));
		calendar.set(Calendar.MINUTE, currentYear + randomCalendar.nextInt(59));
		calendar.set(Calendar.SECOND, currentYear + randomCalendar.nextInt(59));
		calendar.set(Calendar.MILLISECOND, currentYear + randomCalendar.nextInt(998));
		return calendar.getTime();
	}

	public static synchronized int randomPositiveNumber() {
		return randomPositiveNumber(5);
	}

	public static synchronized int randomPositiveNumber(int numberSize) {
		if (numberSize > INT_MAX_VALUE_LENGTH) {
			numberSize = INT_MAX_VALUE_LENGTH;
		}
		Random random = new Random();
		StringBuilder stringBuilderRandomPositiveNumber = new StringBuilder();
		for (int index = 0; index < numberSize; index++) {
			stringBuilderRandomPositiveNumber.append(random.nextInt(10));
		}
		return Integer.valueOf(stringBuilderRandomPositiveNumber.toString());
	}

	public static synchronized String randomString() {
		return randomString(10);
	}

	public static synchronized String randomString(int requiredStringLength) {
		Random random = new Random();
		StringBuilder stringBuilderRandomString = new StringBuilder();
		for (int index = 0; index < requiredStringLength; index++) {
			stringBuilderRandomString.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length() - 1)));
		}
		return stringBuilderRandomString.toString();
	}

	public static synchronized String exceptionStactTraceAsString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public static synchronized String objectArrayAsString(Object... objects) {
		if (objects.length <= 0) {
			return EMPTY_STRING;
		}
		StringBuilder stringBuilderObjects = new StringBuilder();
		stringBuilderObjects.append(SQUARE_BRACKET_OPEN);
		for (Object object : objects) {
			if (object instanceof String) {
				stringBuilderObjects.append(object.toString() + COMMA_AND_SPACE);
			} else {
				stringBuilderObjects.append(
						ANGLE_BRACKET_OPEN + object.getClass().getSimpleName() + ANGLE_BRACKET_CLOSE + COMMA_AND_SPACE);
			}
		}
		stringBuilderObjects.delete(stringBuilderObjects.lastIndexOf(COMMA_AND_SPACE), stringBuilderObjects.length());
		stringBuilderObjects.append(SQUARE_BRACKET_CLOSE);
		return stringBuilderObjects.toString();
	}

	public static synchronized String argumentsAsAppendableString(boolean typeRequired, Object... objects) {
		StringBuilder stringBuilderObjects = new StringBuilder();
		for (Object object : objects) {
			if (object != null && isBaseClass(object)) {
				stringBuilderObjects.append(POUND_SIGN + String.valueOf(object));
			} else if (typeRequired) {
				stringBuilderObjects.append(
						POUND_SIGN + SQUARE_BRACKET_OPEN + object.getClass().getSimpleName() + SQUARE_BRACKET_CLOSE);
			}
		}
		return stringBuilderObjects.toString();
	}

	private static boolean isBaseClass(Object object) {
		for (Class<?> currentClass : baseClasses) {
			if (object.getClass().equals(currentClass)) {
				return true;
			}
		}
		return false;
	}

	public static synchronized String appendSuffixPoundSign(String prefix) {
		return prefix + POUND_SIGN;
	}

}
