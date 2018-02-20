package fr.davidson.acb.springbatch.importacb.config;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import fr.davidson.acb.springbatch.importacb.listeners.JobCompletionNotificationListener;
import fr.davidson.acb.springbatch.importacb.step.AlerteCompteBancaireItemProcessor;
import fr.davidson.acb.springbatch.importacb.step.AlerteCompteBancaireItemReader;
import fr.davidson.acb.springbatch.importacb.step.AlerteCompteBancaireItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobParameterExecutionContextCopyListener;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchAlerteCompteBancaireConfiguration {

    public static final String JOB_NAME = "IMPORT_ALERTE_COMPTE_BANCAIRE";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;


    @StepScope
    @Bean
    public FlatFileItemReader<AlerteCompteBancaireDto> reader() {
        return new AlerteCompteBancaireItemReader();
    }


    @StepScope
    @Bean
    public AlerteCompteBancaireItemProcessor processor() {
        return new AlerteCompteBancaireItemProcessor();
    }

    @StepScope
    @Bean
    public JdbcBatchItemWriter<AlerteCompteBancaireDto> writer() {
        return new AlerteCompteBancaireItemWriter(dataSource);
    }


    @Bean
    public Job importAlerteCompteBancaire(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(illReadFileAndInsertStep())
                .end()
                .build();
    }

    @Bean
    public Step illReadFileAndInsertStep() {
        return stepBuilderFactory.get("illReadFileAndInsertStep")
                .<AlerteCompteBancaireDto, AlerteCompteBancaireDto> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(new JobParameterExecutionContextCopyListener())
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
