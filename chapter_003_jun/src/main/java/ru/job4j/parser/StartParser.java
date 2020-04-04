package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class StartParser {
    private static final Logger LOG = LogManager.getLogger(StartParser.class.getName());

    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(PJob.class).build();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(cronSchedule(new PConfig().get("cron.time")))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}