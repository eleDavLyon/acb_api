-- Création de la table des alertes sur compte bancaire
CREATE TABLE T_ALERTE_COMPTE_BANCAIRE(
  A_ID BIGINT NOT NULL,
  A_NUM_COMPTE VARCHAR(30),
  A_DATE VARCHAR(50),
  A_SOLDE VARCHAR(100),
  A_DERNIERE_OPERATION VARCHAR(255),
  A_MAIL_CLIENT VARCHAR(255),
  CREATE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
  UPDATE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
  PRIMARY KEY(A_ID)
);

-- Commentaires sur la table T_ALERTE_COMPTE_BANCAIRE et ses colonnes
COMMENT ON TABLE T_ALERTE_COMPTE_BANCAIRE IS 'Table des alertes sur compte bancaire';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.A_ID IS 'ID de l''alerte';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.A_NUM_COMPTE IS 'Numero (masqué) du compte bancaire';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.A_DATE IS 'Date du solde';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.A_DERNIERE_OPERATION IS 'Dernière opération sur le compte bancaire';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.A_MAIL_CLIENT IS 'Mail du client';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.CREATE_DATE IS 'Date de création (colonne technique)';
COMMENT ON COLUMN T_ALERTE_COMPTE_BANCAIRE.UPDATE_DATE IS 'Date de dernière mise à jour (colonne technique)';

-- Création d'une sequence pour gérer les identifiants techniques des alertes
CREATE SEQUENCE ALERTE_COMPTE_BANCAIRE_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;