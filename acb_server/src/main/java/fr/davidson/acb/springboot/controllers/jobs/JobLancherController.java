package fr.davidson.acb.springboot.controllers.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.davidson.acb.springbatch.importacb.step.AlerteCompteBancaireItemReader.FILE_PATH;

@RestController
public class JobLancherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importAlerteCompteBancaire;

    @Value("${input.file.dir}")
    private String inputDir;

    @RequestMapping("/launchImportAlertCompte/{filename}")
    public String handle(@PathVariable("filename") String filename) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString(FILE_PATH, inputDir+filename)
                    .addLong("time",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(importAlerteCompteBancaire, jobParameters);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return "Done";
    }
}
