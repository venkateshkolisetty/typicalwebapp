/**
 * 
 */
package com.mevenk.typicalwebapp.bean;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomPositiveNumber;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.mevenk.typicalwebapp.exception.DisgracedInvocationException;

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

	private static final transient String TYPICALWEBAPPBEAN_NAME = "TypicalWebAppBean";

	static final transient String TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME = "typicalwebappbean";

	public static final transient Gson gsonTypicalWebAppBean = new GsonBuilder().enableComplexMapKeySerialization()
			.serializeNulls().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
			.excludeFieldsWithoutExposeAnnotation().create();

	private static final transient String HEADER_PARAM_STATUS = TYPICALWEBAPPBEAN_NAME + " Status";
	private static final transient String HEADER_PARAM_AVAILABLE = TYPICALWEBAPPBEAN_NAME + " Available";
	private static final transient String HEADER_PARAM_ADD = TYPICALWEBAPPBEAN_NAME + " Add";
	private static final transient String HEADER_PARAM_UPDATE = TYPICALWEBAPPBEAN_NAME + " Update";
	private static final transient String HEADER_PARAM_DELETE = TYPICALWEBAPPBEAN_NAME + " Delete";

	public enum TypicalWebAppBeanInvocationService {
		SUCCESS, ERROR, AVAILABLE, NOT_AVAILABLE, ADD_SUCCESS, ADD_ERROR, UPDATE_SUCCESS, UPDATE_ERROR, DELETE_SUCCESS, DELETE_ERROR;

		private String headerParam;
		private String value;

		/**
		 * @return the headerParam
		 */
		public String getHeaderParam() {
			switch (this) {
			case SUCCESS:
				headerParam = HEADER_PARAM_STATUS;
				value = "Success";
				break;
			case ERROR:
				headerParam = HEADER_PARAM_STATUS;
				value = "Error";
				break;
			case AVAILABLE:
				headerParam = HEADER_PARAM_AVAILABLE;
				value = "Available";
				break;
			case NOT_AVAILABLE:
				headerParam = HEADER_PARAM_AVAILABLE;
				value = "Not Available";
				break;
			case ADD_SUCCESS:
				headerParam = HEADER_PARAM_ADD;
				value = "Add Success";
				break;
			case ADD_ERROR:
				headerParam = HEADER_PARAM_ADD;
				value = "Add Error";
				break;
			case UPDATE_SUCCESS:
				headerParam = HEADER_PARAM_UPDATE;
				value = "Update Success";
				break;
			case UPDATE_ERROR:
				headerParam = HEADER_PARAM_UPDATE;
				value = "Update Error";
				break;
			case DELETE_SUCCESS:
				headerParam = HEADER_PARAM_DELETE;
				value = "Delete Success";
				break;
			case DELETE_ERROR:
				headerParam = HEADER_PARAM_DELETE;
				value = "Delete Error";
				break;
			default:
				throw new DisgracedInvocationException();
			}
			return headerParam;
		}

		public String headerValue() {
			return value;
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

	protected TypicalWebAppBean(int typicalWebAppBeanId) {
		this.typicalWebAppBeanId = typicalWebAppBeanId;
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
