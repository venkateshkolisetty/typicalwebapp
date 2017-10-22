/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.CONFIG;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.AT_SIGH;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.BRACES_CLOSE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.BRACES_OPEN;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.DOUBLE_SPACE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.EMPTY_STRING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.LINE_SEPARATOR;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SINGLE_COLUN_AND_SPACE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppConstants.SINGLE_SPACE;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.exceptionStactTraceAsString;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Venkatesh
 *
 */
public class TypicalWebAppSourceBean implements InitializingBean, DisposableBean {

	private static final Logger log = LogManager.getLogger(TypicalWebAppSourceBean.class);

	private String beanClassName;

	/**
	 * @param beanClassName
	 */
	public TypicalWebAppSourceBean() {
		this.beanClassName = this.getClass().getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		log.log(CONFIG, "Properties set for {}", beanClassName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		log.log(CONFIG, "Destroying {}", beanClassName);

	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getName() + AT_SIGH + Integer.toHexString(this.hashCode()));
		result.append(SINGLE_SPACE + "Object" + SINGLE_SPACE + BRACES_OPEN);
		result.append(LINE_SEPARATOR);

		Field[] fields = this.getClass().getFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append(DOUBLE_SPACE);
			try {
				result.append(field.getName());
				result.append(SINGLE_COLUN_AND_SPACE);
				result.append(field.get(this));
				result.append(LINE_SEPARATOR);
			} catch (IllegalAccessException exception) {
				log.log(CONFIG, "Error generating toString during field {}->{}", field.getName(),
						this.getClass().getSimpleName());
				log.error("{}", exceptionStactTraceAsString(exception));
			}

		}

		Method[] methods = this.getClass().getMethods();

		String methodName = null;
		for (Method method : methods) {
			try {
				methodName = method.getName();
				if (methodName.startsWith("get") && !methodName.equalsIgnoreCase("getClass")) {
					result.append(DOUBLE_SPACE);
					// result.append(methodName.substring(3).replaceFirst("\\b([A-Z])(.*)",
					// "\\L$1$2"));
					result.append(new String(EMPTY_STRING + methodName.charAt(3)).toLowerCase() + EMPTY_STRING
							+ methodName.substring(4));
					result.append(SINGLE_COLUN_AND_SPACE);
					result.append(method.invoke(this));
					result.append(LINE_SEPARATOR);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
				log.log(CONFIG, "Error generating toString during method {}->{}", methodName,
						this.getClass().getSimpleName());
				log.error("{}", exceptionStactTraceAsString(exception));
			}
		}

		result.append(BRACES_CLOSE);

		return result.toString();
	}

}
