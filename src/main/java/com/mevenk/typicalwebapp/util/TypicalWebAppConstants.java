/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import java.io.File;

import com.mevenk.typicalwebapp.exception.DisgracedConstructorInvocationException;

/**
 * @author VENKATESH
 *
 */
public final class TypicalWebAppConstants {

	public static final String TYPICAL_WEB_APP_BASE_PACKAGES = "com.mevenk.typicalwebapp";
	public static final String PROPERTY_SOURCE_TYPICAL_WEB_APP_PROPERTIES_FILE_SOURCE = "${typicalWebappPropertiesFileSource}";

	private TypicalWebAppConstants() {
		throw new DisgracedConstructorInvocationException(this.getClass());
	}

	public static final String APP_BASE_DIR = System.getProperty("user.dir");

	public static final String LINE_SEPARATOR = System.lineSeparator();
	public static final String FILE_SEPARATOR = File.separator;

	public static final String ERROR = "ERROR";

	public static final String SLASH = "/";
	public static final String BACKSLASH = "\\";
	public static final String ASTERISK = "*";
	public static final String EMPTY_STRING = "";
	public static final String UNDERSCORE = "_";
	public static final String HYPHEN = "-";
	public static final String COMMA_AND_SPACE = ", ";
	public static final String POUND_SIGN = "#";
	public static final String AT_SIGH = "@";
	public static final String DOLLAR_SIGH = "$";

	public static final String SINGLE_SPACE = " ";
	public static final String DOUBLE_SPACE = "  ";
	public static final String TAB_SPACE = "    ";
	public static final String SINGLE_COLUN_AND_SPACE = ": ";
	public static final String SPACE_AROUND_SINGLE_COLUN = " : ";
	public static final String SPACE_AROUND_DOUBLE_COLUN = " :: ";
	public static final String SPACE_AROUND_EQUALS = " = ";

	public static final String PARENTHESES_OPEN = "(";
	public static final String PARENTHESES_CLOSE = ")";

	public static final String BRACES_OPEN = "{";
	public static final String BRACES_CLOSE = "}";

	public static final String SQUARE_BRACKET_OPEN = "[";
	public static final String SQUARE_BRACKET_CLOSE = "]";

	public static final String ANGLE_BRACKET_OPEN = "<";
	public static final String ANGLE_BRACKET_CLOSE = ">";

	public static final String EXT_JSON = ".json";

}
