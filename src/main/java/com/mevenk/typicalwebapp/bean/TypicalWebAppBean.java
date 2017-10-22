/**
 * 
 */
package com.mevenk.typicalwebapp.bean;

import static com.mevenk.typicalwebapp.bean.TypicalWebAppBean.TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.randomPositiveNumber;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.sun.xml.internal.txw2.annotation.XmlAttribute;

/**
 * @author Venkatesh
 *
 */
@XmlRootElement(name = TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME)
public class TypicalWebAppBean {

	static final String TYPICAL_WEB_APP_BEAN_XML_ROOT_ELEMENT_NAME = "typicalwebappbean";

	public static final Gson gsonTypicalWebAppBean = new GsonBuilder().enableComplexMapKeySerialization()
			.serializeNulls().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
			.excludeFieldsWithoutExposeAnnotation().create();

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_TYPICAL_WEB_APP_BEAN = new SimpleDateFormat(
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
