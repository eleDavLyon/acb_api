package fr.davidson.acb.business.service.impl;

import fr.davidson.acb.business.config.CustomMustacheConfiguration;
import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import fr.davidson.acb.business.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Implémentation des services du contrat d'interface des mails.
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MailServiceImpl implements MailService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CustomMustacheConfiguration customMustacheConfiguration;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessage(AlerteCompteBancaireDto alerteCompteBancaireDto) {
        boolean result = false;

        if(alerteCompteBancaireDto==null){
            return result;
        }
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(messageSource.getMessage("mail.subject", null, null));
            message.setFrom(messageSource.getMessage("mail.from", null, null));
            message.setTo(alerteCompteBancaireDto.getMailClient());
            Map<String, Object> datas = new HashMap<>();
            datas.put("numCompte", alerteCompteBancaireDto.getNumCompte());
            datas.put("date", alerteCompteBancaireDto.getDate());
            datas.put("solde", alerteCompteBancaireDto.getSolde());
            datas.put("dernierOperation", alerteCompteBancaireDto.getDernierOperation());
            Reader template = customMustacheConfiguration.mustacheTemplateLoader().getTemplate("alerte_compte_bancaire");
            String content = customMustacheConfiguration.mustacheCompiler(customMustacheConfiguration.mustacheTemplateLoader())
                    .compile(template)
                    .execute(datas);
            message.setText(content, true);
            mailSender.send(mimeMessage);
            result = true;
        } catch (Exception exception) {
//          TODO  throw new TechniqueException(messageSource.getMessage("erreur.envoi.mail.technique", null, null), exception);
        }
        return result;
    }
}
