/**
 * 
 */
package com.mevenk.typicalwebapp.bean;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;
import com.mevenk.typicalwebapp.exception.DisgracedInvocationException;

/**
 * @author Venkatesh
 *
 */
@XmlRootElement(name = TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME)
public class TypicalWebAppErrorBean extends TypicalWebAppBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8178108610843859468L;

	public enum TypicalWebAppErrorBeanError {
		BEAN_NOT_AVAILABLE;

		public String reason() {
			switch (this) {
			case BEAN_NOT_AVAILABLE:
				return "Required Bean Not Available";
			default:
				throw new DisgracedInvocationException();
			}
		}
	}

	@Expose
	private Date date;
	@Expose
	private String reason;

	public TypicalWebAppErrorBean(int typicalWebAppBeanId, TypicalWebAppErrorBeanError typicalWebAppErrorBeanError) {
		super(typicalWebAppBeanId);
		this.date = new Date();
		this.reason = typicalWebAppErrorBeanError.reason();
	}

	/**
	 * @return the date
	 */
	@XmlElement
	public Date getDate() {
		return date;
	}

	/**
	 * @return the reason
	 */
	@XmlElement
	public String getReason() {
		return reason;
	}

}
