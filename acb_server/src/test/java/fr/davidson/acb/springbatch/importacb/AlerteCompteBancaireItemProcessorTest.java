package fr.davidson.acb.springbatch.importacb;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import fr.davidson.acb.springbatch.AbstractBatchTest;
import fr.davidson.acb.springbatch.importacb.step.AlerteCompteBancaireItemProcessor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlerteCompteBancaireItemProcessorTest extends AbstractBatchTest {

    @Autowired
    private AlerteCompteBancaireItemProcessor alerteCompteBancaireItemProcessor;

    @Test
    public void whenProcessNullAllertThen() throws Exception{
        AlerteCompteBancaireDto alerteCompteBancaireDto = AlerteCompteBancaireDto.builder()
                .numCompte("NUMCOMPTE")
                .date("18/02/2018")
                .dernierOperation("LAST OPERATION")
                .mailClient("MAIL_ADDRESS")
                .solde("12000")
                .build();
        alerteCompteBancaireItemProcessor.process(alerteCompteBancaireDto);
//        TODO
    }
}
