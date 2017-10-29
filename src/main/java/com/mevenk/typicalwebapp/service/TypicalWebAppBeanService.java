/**
 * 
 */
package com.mevenk.typicalwebapp.service;

import com.mevenk.typicalwebapp.bean.TypicalWebAppBean;

/**
 * @author Venkatesh
 *
 */
public interface TypicalWebAppBeanService {

	boolean isTypicalWebAppBeanAvailable(int typicalWebAppBeanId);

	TypicalWebAppBean addTypicalWebAppBean();

	TypicalWebAppBean getTypicalWebAppBean(int typicalWebAppBeanId);

	TypicalWebAppBean modifyTypicalWebAppBean(TypicalWebAppBean typicalWebAppBean);

	boolean deleteTypicalWebAppBean(int typicalWebAppBeanId);

}
