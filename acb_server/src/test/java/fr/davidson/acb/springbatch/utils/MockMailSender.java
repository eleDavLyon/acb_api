package fr.davidson.acb.springbatch.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

/**
 *
 * Mock pour ne pas envoyer de mail pour les tests unitaires.
 *
 */
public class MockMailSender extends JavaMailSenderImpl {

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
//        Dans les cas de tests unitaires, on n'envoie pas de mail.
    }
}
