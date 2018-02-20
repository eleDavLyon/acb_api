package fr.davidson.acb.business.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Objet représentant les informations d'une alerte sur compte bancaire
 *
 */
@Data
@Builder
@ToString(exclude = {})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {})
public class AlerteCompteBancaireDto extends BaseDTO{

    private String numCompte;
    private String date;
    private String solde;
    private String dernierOperation;
    private String mailClient;

}
