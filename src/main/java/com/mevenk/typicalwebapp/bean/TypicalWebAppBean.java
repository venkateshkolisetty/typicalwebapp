/**
 * 
 */
package com.mevenk.typicalwebapp.bean;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomPositiveNumber;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.mevenk.typicalwebapp.exception.DisgracedInvocationException;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * @author Venkatesh
 *
 */
@XmlRootElement(name = TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME)
public class TypicalWebAppBean implements Serializable {

	/**
	 * 
	 */
	private static final transient long serialVersionUID = 5640900689788374670L;

	private static final transient String TYPICALWEBAPPBEAN = "TypicalWebAppBean";

	static final transient String TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME = "typicalwebappbean";

	public static final transient Gson gsonTypicalWebAppBean = new GsonBuilder().enableComplexMapKeySerialization()
			.serializeNulls().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
			.excludeFieldsWithoutExposeAnnotation().create();

	private static final transient String HEADER_PARAM_STATUS = TYPICALWEBAPPBEAN + " Status";
	private static final transient String HEADER_PARAM_AVAILABLE = TYPICALWEBAPPBEAN + " Available";
	private static final transient String HEADER_PARAM_ADD = TYPICALWEBAPPBEAN + " Add";
	private static final transient String HEADER_PARAM_UPDATE = TYPICALWEBAPPBEAN + " Update";
	private static final transient String HEADER_PARAM_DELETE = TYPICALWEBAPPBEAN + " Delete";

	public enum TypicalWebAppBeanInvocationService {
		SUCCESS, ERROR, AVAILABLE, NOT_AVAILABLE, ADD_SUCCESS, ADD_ERROR, UPDATE_SUCCESS, UPDATE_ERROR, DELETE_SUCCESS, DELETE_ERROR;

		private String headerParam;

		public String value() {
			switch (this) {
			case SUCCESS:
				headerParam = HEADER_PARAM_STATUS;
				return "Success";
			case ERROR:
				headerParam = HEADER_PARAM_STATUS;
				return "Error";
			case AVAILABLE:
				headerParam = HEADER_PARAM_AVAILABLE;
				return "Available";
			case NOT_AVAILABLE:
				headerParam = HEADER_PARAM_AVAILABLE;
				return "Not Available";
			case ADD_SUCCESS:
				headerParam = HEADER_PARAM_ADD;
				return "Add Success";
			case ADD_ERROR:
				headerParam = HEADER_PARAM_ADD;
				return "Add Error";
			case UPDATE_SUCCESS:
				headerParam = HEADER_PARAM_UPDATE;
				return "Update Success";
			case UPDATE_ERROR:
				headerParam = HEADER_PARAM_UPDATE;
				return "Update Error";
			case DELETE_SUCCESS:
				headerParam = HEADER_PARAM_DELETE;
				return "Delete Success";
			case DELETE_ERROR:
				headerParam = HEADER_PARAM_DELETE;
				return "Delete Error";
			default:
				throw new DisgracedInvocationException();
			}
		}

		/**
		 * @return the headerParam
		 */
		public String getHeaderParam() {
			return headerParam;
		}
	}

	private static final transient SimpleDateFormat SIMPLE_DATE_FORMAT_TYPICAL_WEB_APP_BEAN = new SimpleDateFormat(
			"yyyy-MM-dd'T'kk:mm:ss.S");

	@Expose
	private int typicalWebAppBeanId = -1;
	@Expose
	private String randomString;
	@Expose
	private int randomInteger;
	@Expose
	private Date randomDate;

	/**
	 * @param randomString
	 * @param randomInteger
	 * @param randomDate
	 */
	public TypicalWebAppBean(String randomString, int randomInteger, Date randomDate) {
		this.typicalWebAppBeanId = randomPositiveNumber();
		this.randomString = randomString;
		this.randomInteger = randomInteger;
		this.randomDate = randomDate;
	}

	/**
	 * @return the typicalWebAppBeanId
	 */
	@XmlAttribute
	public int getTypicalWebAppBeanId() {
		return typicalWebAppBeanId;
	}

	/**
	 * @return the randomString
	 */
	@XmlElement
	public String getRandomString() {
		return randomString;
	}

	/**
	 * @param randomString
	 *            the randomString to set
	 */
	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	/**
	 * @return the randomInteger
	 */
	@XmlElement
	public int getRandomInteger() {
		return randomInteger;
	}

	/**
	 * @param randomInteger
	 *            the randomInteger to set
	 */
	public void setRandomInteger(int randomInteger) {
		this.randomInteger = randomInteger;
	}

	/**
	 * @return the randomDate
	 */
	@XmlElement
	public Date getRandomDate() {
		return randomDate;
	}

	/**
	 * @param randomDate
	 *            the randomDate to set
	 */
	public void setRandomDate(Date randomDate) {
		this.randomDate = randomDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TypicalWebAppBean [typicalWebAppBeanId=" + typicalWebAppBeanId + ", "
				+ (randomString != null ? "randomString=" + randomString + ", " : "") + "randomInteger=" + randomInteger
				+ ", "
				+ (randomDate != null ? "randomDate=" + SIMPLE_DATE_FORMAT_TYPICAL_WEB_APP_BEAN.format(randomDate) : "")
				+ "]";
	}

}
