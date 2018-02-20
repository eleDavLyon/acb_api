package fr.davidson.acb.springbatch.importacb.step;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

public class AlerteCompteBancaireItemReader extends FlatFileItemReader<AlerteCompteBancaireDto>{
    public static final String FILE_PATH = "FILE_PATH";

    @Value("#{jobParameters["+ FILE_PATH +"]}")
    private String filePath;


    public AlerteCompteBancaireItemReader(){
        super();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setLineMapper(lineMapper());
        setResource(new FileSystemResource(filePath));
    }

    /**
     * @return line tokenizer
     */
    private LineTokenizer tokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"numCompte", "date", "solde", "dernierOperation", "mailClient"});
        return tokenizer;
    }

    /**
     * @return line mapper
     */
    private LineMapper lineMapper() {
        DefaultLineMapper<AlerteCompteBancaireDto> lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(tokenizer());
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<AlerteCompteBancaireDto>() {{
            setTargetType(AlerteCompteBancaireDto.class);
        }});
        return lineMapper;
    }

}
