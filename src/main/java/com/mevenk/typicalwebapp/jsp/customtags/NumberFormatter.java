/**
 *
 */
package com.mevenk.typicalwebapp.jsp.customtags;

import java.text.DecimalFormat;

import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author venky
 *
 */
public class NumberFormatter extends TagSupport {
	/**
	 *
	 */
	private static final long serialVersionUID = 8771449630548530694L;

	private static final Logger LOG = LogManager.getLogger(NumberFormatter.class);

	private String format;
	private String number;

	public void setFormat(String format) {
		this.format = format;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public int doStartTag() throws SkipPageException {
		LOG.info("Number is:" + number);
		LOG.info("Format is:" + format);
		try {
			double amount = Double.parseDouble(number);
			DecimalFormat formatter = new DecimalFormat(format);
			String formattedNumber = formatter.format(amount);
			pageContext.getOut().print(formattedNumber);
			return EVAL_PAGE;

		} catch (Exception e) {
			LOG.error(e);
			// stop page from loading further by throwing SkipPageException
			throw new SkipPageException("Exception in formatting " + number + " with format " + format);
		}
	}
}
