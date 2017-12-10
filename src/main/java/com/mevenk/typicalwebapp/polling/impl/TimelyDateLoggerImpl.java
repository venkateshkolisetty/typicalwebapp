/**
 * 
 */
package com.mevenk.typicalwebapp.polling.impl;

import static com.mevenk.typicalwebapp.config.TypicalWebAppLogger.POLLING;
import static com.mevenk.typicalwebapp.util.TypicalWebAppExpressions.DATE_POLLING_CRON_EXPRESSION;
import static com.mevenk.typicalwebapp.util.TypicalWebAppUtil.currentDateForTimelyDateLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mevenk.typicalwebapp.polling.TimelyDateLogger;

/**
 * @author Venkatesh
 *
 */
@Component
public class TimelyDateLoggerImpl implements TimelyDateLogger {

	private static final Logger log = LogManager.getLogger(TimelyDateLoggerImpl.class);

	@Scheduled(cron = DATE_POLLING_CRON_EXPRESSION)
	@Override
	public void timelyDatePollingFromCron() {
		log.log(POLLING, "Polling..... | {}", currentDateForTimelyDateLogger());
	}

}
