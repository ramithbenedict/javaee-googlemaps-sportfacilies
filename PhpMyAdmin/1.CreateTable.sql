#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Installation
#------------------------------------------------------------

CREATE TABLE installation(
        numInstallation Varchar (250) NOT NULL ,
        nomInstallation Varchar (250) ,
        nomEquipement   Varchar (250) ,
        departement     Varchar (250) ,
        commune         Varchar (250) ,
        gpsX            Float ,
        gpsY            Float ,
        handicapM       Boolean,
        handicapS       Boolean,
        longueur        Float ,
        largeur         Float ,
        hauteur         Float ,
        eclairage       Boolean,
        numUtilisateur  Int ,
        adresse         Varchar (1000) ,
        PRIMARY KEY (numInstallation )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Activite
#------------------------------------------------------------

CREATE TABLE activite(
        numActivite int (11) Auto_increment  NOT NULL ,
        nomActivite Varchar (250) ,
        PRIMARY KEY (numActivite )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE utilisateur(
        numUtilisateur int (11) Auto_increment  NOT NULL ,
        nom            Varchar (250) ,
        prenom         Varchar (250) ,
        email          Varchar (250) ,
        telephone      Varchar (250) ,
        motDePasse     Varchar (250) ,
        PRIMARY KEY (numUtilisateur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Creneau
#------------------------------------------------------------

CREATE TABLE creneau(
        numCreneau      int (11) Auto_increment  NOT NULL ,
        dateCreneau     Date ,
        heure           Varchar (250) ,
        disponible      Boolean NOT NULL ,
        numUtilisateur  Int ,
        numInstallation Varchar (250) ,
        prix            Float,
        commentaire     Varchar (1000),
        note 			Int,
        PRIMARY KEY (numCreneau )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Historique
#------------------------------------------------------------

CREATE TABLE historique(
        numHistorique  int (11) Auto_increment  NOT NULL ,
        activite       Varchar (250) ,
        dateRecherche  Date ,
        lieuRecherche  Varchar (250) ,
        numUtilisateur Int ,
        PRIMARY KEY (numHistorique )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: InstallationActivite
#------------------------------------------------------------

CREATE TABLE installationactivite(
        numInstallation Varchar (250) NOT NULL ,
        numActivite     Int NOT NULL ,
        PRIMARY KEY (numInstallation ,numActivite )
)ENGINE=InnoDB;

ALTER TABLE installation ADD CONSTRAINT FK_installation_numutilisateur FOREIGN KEY (numutilisateur) REFERENCES utilisateur(numutilisateur);
ALTER TABLE creneau ADD CONSTRAINT FK_creneau_numutilisateur FOREIGN KEY (numutilisateur) REFERENCES utilisateur(numutilisateur);
ALTER TABLE creneau ADD CONSTRAINT FK_creneau_numinstallation FOREIGN KEY (numinstallation) REFERENCES installation(numinstallation);
ALTER TABLE historique ADD CONSTRAINT FK_historique_numutilisateur FOREIGN KEY (numutilisateur) REFERENCES utilisateur(numutilisateur);
ALTER TABLE installationactivite ADD CONSTRAINT FK_installationactivite_numinstallation FOREIGN KEY (numinstallation) REFERENCES installation(numinstallation);
ALTER TABLE installationactivite ADD CONSTRAINT FK_installationactivite_numactivite FOREIGN KEY (numactivite) REFERENCES activite(numactivite);
