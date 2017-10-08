/**
 * 
 */
package com.mevenk.typicalwebapp.pooling.impl;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.POOLING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.currentDateForTimelyDateLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mevenk.typicalwebapp.pooling.TimelyDateLogger;

/**
 * @author Venkatesh
 *
 */
@Component
public class TimelyDateLoggerImpl implements TimelyDateLogger {

	private static final Logger log = LogManager.getLogger(TimelyDateLoggerImpl.class);

	@Scheduled(cron = "${datePoolingCronExpression}")
	@Override
	public void fixedRateDatePooling() {
		log.log(POOLING, "Pooling..... | {}", currentDateForTimelyDateLogger());
	}

}
