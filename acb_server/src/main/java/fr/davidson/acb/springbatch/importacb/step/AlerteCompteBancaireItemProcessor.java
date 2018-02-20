package fr.davidson.acb.springbatch.importacb.step;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import fr.davidson.acb.business.service.MailService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class AlerteCompteBancaireItemProcessor implements ItemProcessor<AlerteCompteBancaireDto, AlerteCompteBancaireDto> {

    @Autowired
    private MailService mailService;

    @Override
    public AlerteCompteBancaireDto process(AlerteCompteBancaireDto alerteCompteBancaireDto) throws Exception {
        mailService.sendMessage(alerteCompteBancaireDto);
        return alerteCompteBancaireDto;
    }
}
