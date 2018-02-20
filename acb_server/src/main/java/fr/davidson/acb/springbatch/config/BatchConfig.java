package fr.davidson.acb.springbatch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public SimpleJobLauncher jobLauncher(final JobRepository jobRepository) {
        return new SimpleJobLauncher() {{
            setJobRepository(jobRepository);
        }};
    }
}
