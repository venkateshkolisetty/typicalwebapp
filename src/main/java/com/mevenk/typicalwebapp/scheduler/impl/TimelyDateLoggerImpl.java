/**
 * 
 */
package com.mevenk.typicalwebapp.scheduler.impl;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.POOLING;
import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.THREAD_CONTEXT_KEY;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.currentDateForTimelyDateLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mevenk.typicalwebapp.scheduler.TimelyDateLogger;

/**
 * @author Venkatesh
 *
 */
@Component
public class TimelyDateLoggerImpl implements TimelyDateLogger {

	private static final Logger log = LogManager.getLogger(TimelyDateLoggerImpl.class);

	@Scheduled(cron = "${typicalwebappDatePoolingCron}")
	@Override
	public void fixedRateDatePooling() {
		ThreadContext.put(THREAD_CONTEXT_KEY, "FixedRateDatePooling");
		System.out.println("Pooling date..... | The time is now " + currentDateForTimelyDateLogger());
		log.log(POOLING, "Pooling date..... | The time is now {}", currentDateForTimelyDateLogger());
	}

}
