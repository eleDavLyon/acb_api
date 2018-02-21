package fr.davidson.acb.springbatch.importacb.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.BatchStatus.COMPLETED;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == COMPLETED) {
            log.info("End of job");
            int count = jdbcTemplate.queryForObject("SELECT count(*) FROM T_ALERTE_COMPTE_BANCAIRE", new Object[] {}, Integer.class);
            log.info("{} alertes in the database.", count);
            }
        }
}