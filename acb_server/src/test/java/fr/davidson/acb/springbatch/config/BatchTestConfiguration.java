package fr.davidson.acb.springbatch.config;

import fr.davidson.acb.springbatch.utils.MockMailSender;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Configuration pour les tests des programmes Batch.
 */
@Configuration
@ComponentScan({"fr.davidson.acb"})
@PropertySource(value = "classpath:/application.properties")
public class BatchTestConfiguration {

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean(name = "javaMailSender")
    public JavaMailSender javaMailSender() {
        return new MockMailSender();
    }
}
