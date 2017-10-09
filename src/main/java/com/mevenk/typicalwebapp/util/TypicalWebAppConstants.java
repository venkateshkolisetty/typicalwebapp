/**
 * 
 */
package com.mevenk.typicalwebapp.util;

import java.io.File;

/**
 * @author VENKATESH
 *
 */
public interface TypicalWebAppConstants {

	String APP_BASE_DIR = System.getProperty("user.dir");

	String LINE_SEPARATOR = System.lineSeparator();
	String FILE_SEPARATOR = File.separator;

	String EMPTY_STRING = "";
	String UNDERSCORE = "_";
	String HYPHEN = "-";
	String COMMA_AND_SPACE = ", ";
	String POUND_SIGN = "#";

	String TAB_SPACE = "    ";
	String TAB_SPACE_AROUND_SINGLE_COLUN = " : ";
	String TAB_SPACE_AROUND_DOUBLE_COLUN = " :: ";
	String TAB_SPACE_AROUND_EQUALS = " = ";

	String SQUARE_BRACKET_OPEN = "[";
	String SQUARE_BRACKET_CLOSE = "]";

	String ANGLE_BRACKET_OPEN = "<";
	String ANGLE_BRACKET_CLOSE = ">";

}
