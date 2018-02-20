package fr.davidson.acb.springbatch.importacb;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import fr.davidson.acb.springbatch.AbstractBatchTest;
import org.junit.Test;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
public class AlerteCompteBancaireItemWriterTest extends AbstractBatchTest{

    @Autowired
    private JdbcBatchItemWriter<AlerteCompteBancaireDto> alerteCompteBancaireItemWriter;

    @Test
    public void testPersonItemWriter() throws Exception{
        AlerteCompteBancaireDto alerteCompteBancaireDto = AlerteCompteBancaireDto.builder()
                .numCompte("NUMCOMPTE")
                .date("18/02/2018")
                .dernierOperation("LAST OPERATION")
                .mailClient("MAIL_ADDRESS")
                .solde("12000")
                .build();
        alerteCompteBancaireItemWriter.write(Arrays.asList(alerteCompteBancaireDto));
        Long nbAlerts = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM T_ALERTE_COMPTE_BANCAIRE",Long.class);
        assertEquals(1L,nbAlerts.longValue());
    }
}
