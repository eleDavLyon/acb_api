package fr.davidson.acb.springbatch;

import fr.davidson.acb.springbatch.config.BatchTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ContextConfiguration(classes = {BatchTestConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        StepScopeTestExecutionListener.class})
public abstract class AbstractBatchTest {

    @Autowired
    protected JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

}
