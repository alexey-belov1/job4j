package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.sql.Connection;
import java.util.Set;

public class PJob implements Job {

    private static final Logger LOG = LogManager.getLogger(PJob.class.getName());

    @Override
    public void execute(JobExecutionContext context) {
        LOG.info("Start program...");

        Connection connection = PConnectionRollback.create(new PConfig(), false);
        try (PSQL parser = new PSQL(connection)) {
            Set<Vacancy> list = new PHTML().parseVacancies(parser.getLastDate());
            parser.addInBase(list);
            parser.saveDate();
        }

        LOG.info("Programm completed.");
    }
}
