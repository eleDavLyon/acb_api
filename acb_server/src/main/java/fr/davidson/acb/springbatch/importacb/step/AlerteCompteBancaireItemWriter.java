package fr.davidson.acb.springbatch.importacb.step;

import fr.davidson.acb.business.domains.dto.AlerteCompteBancaireDto;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AlerteCompteBancaireItemWriter extends JdbcBatchItemWriter<AlerteCompteBancaireDto> {

    private static final String INSERT_ALERTE_COMPTE_BANCAIRE_REQUEST = "INSERT INTO T_ALERTE_COMPTE_BANCAIRE(A_ID, A_NUM_COMPTE," +
                                                                        "A_DATE, A_SOLDE, A_DERNIERE_OPERATION, A_MAIL_CLIENT) " +
                                                                        " VALUES " +
                                                                        "(nextval('ALERTE_COMPTE_BANCAIRE_SEQ'), :numCompte, :date, " +
                                                                        ":solde, :dernierOperation, :mailClient)";

    public AlerteCompteBancaireItemWriter(DataSource dataSource){
        super();
        setDataSource(dataSource);
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AlerteCompteBancaireDto>());
        setSql(INSERT_ALERTE_COMPTE_BANCAIRE_REQUEST);
    }
}
