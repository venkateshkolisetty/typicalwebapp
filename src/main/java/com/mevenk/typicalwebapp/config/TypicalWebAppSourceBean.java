/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.CONFIG;

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

	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName() + "@" + Integer.toHexString(this.hashCode()));
		result.append(" Object {");
		result.append(newLine);

		Field[] fields = this.getClass().getFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				result.append(field.get(this));
				result.append(newLine);
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}

		}

		Method[] methods = this.getClass().getMethods();

		String methodName = "";
		for (Method method : methods) {
			try {
				methodName = method.getName();
				if (methodName.startsWith("get") && !methodName.equalsIgnoreCase("getClass")) {
					result.append("  ");
					// result.append(methodName.substring(3).replaceFirst("\\b([A-Z])(.*)",
					// "\\L$1$2"));
					result.append(new String("" + methodName.charAt(3)).toLowerCase() + "" + methodName.substring(4));
					result.append(": ");
					result.append(method.invoke(this));
					result.append(newLine);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
				System.out.println(ex);
			}
		}

		result.append("}");

		return result.toString();
	}

}
