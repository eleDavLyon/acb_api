package fr.davidson.acb.business.service;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;

/**
 * Contrat d'interface du service d'envoi de mail.
 *
 */
public interface MailService {

    /**
     * Envoyer un mail d'alerte sur compte
     *
     * @param alerteCompteBancaireDto {@link AlerteCompteBancaireDto}
     * @return
     */
    boolean sendMessage(AlerteCompteBancaireDto alerteCompteBancaireDto);
}
