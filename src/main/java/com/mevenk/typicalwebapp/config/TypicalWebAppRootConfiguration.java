/**
 * 
 */
package com.mevenk.typicalwebapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Venkatesh
 *
 */
@Configuration
@PropertySource("${typicalWebappPropertiesFileSource}")
@EnableScheduling
@EnableWebMvc
@ComponentScan(basePackages = "com.mevenk.typicalwebapp")
public class TypicalWebAppRootConfiguration {

}
