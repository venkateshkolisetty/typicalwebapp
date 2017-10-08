/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import static com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader.CORRELATION_ID_DATE_FORMAT_PATTERN;
import static com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader.MISC_DATE_FORMAT_PATTERN;
import static com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader.TIMELY_LOGGER_DATE_FORMAT_PATTERN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ANGLE_BRACKET_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.ANGLE_BRACKET_OPEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.COMMA_AND_SPACE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.POUND_SIGN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SQUARE_BRACKET_OPEN;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mevenk.typicalwebapp.config.TypicalWebAppPropertiesLoader;

/**
 * @author Venkatesh
 *
 */
public abstract class TypicalWebAppUtil {

	private static final Class<?>[] baseClasses = { String.class, Byte.class, Short.class, Integer.class, Long.class,
			Float.class, Double.class, Boolean.class };

	private static final String PATTERN_SIMPLE_DATE_FORMAT_MISC_DATE = MISC_DATE_FORMAT_PATTERN;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_MISC_DATE = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_MISC_DATE);

	private static final String PATTERN_SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER = TIMELY_LOGGER_DATE_FORMAT_PATTERN;
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER);

	private static final String SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES_PATTERN = TypicalWebAppPropertiesLoader.SOURCE_BEAN_DATE_FORMAT_PATTERN;
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES = new SimpleDateFormat(
			SIMPLE_DATE_FORMAT_SOURCE_BEAN_DATES_PATTERN);

	private static final String PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID = CORRELATION_ID_DATE_FORMAT_PATTERN;
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_CORRELATION_ID = new SimpleDateFormat(
			PATTERN_SIMPLE_DATE_FORMAT_CORRELATION_ID);

	public static String formatDateToString(Date date) {
		return SIMPLE_DATE_FORMAT_MISC_DATE.format(date);
	}

	public static String currentDateForTimelyDateLogger() {
		return SIMPLE_DATE_FORMAT_TIMELY_DATE_LOGGER.format(new Date());
	}

	public static String exceptionStactTraceAsString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public static String objectArrayAsString(Object... objects) {
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

	public static String argumentsAsAppendableString(boolean typeRequired, Object... objects) {
		StringBuilder stringBuilderObjects = new StringBuilder();
		for (Object object : objects) {
			if (object != null && isSimpleObject(object)) {
				stringBuilderObjects.append(POUND_SIGN + String.valueOf(object));
			} else if (typeRequired) {
				stringBuilderObjects.append(POUND_SIGN + object.getClass().getSimpleName());
			}
		}
		return stringBuilderObjects.toString();
	}

	private static boolean isSimpleObject(Object object) {
		if (isBaseClass(object)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isBaseClass(Object object) {
		for (Class<?> currentClass : baseClasses) {
			if (object.getClass().equals(currentClass)) {
				return true;
			}
		}
		return false;
	}

}
