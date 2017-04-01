/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Moms
 */
@Entity
@Table(name = "installation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Installation.findAll", query = "SELECT i FROM Installation i"),
    @NamedQuery(name = "Installation.findByNumInstallation", query = "SELECT i FROM Installation i WHERE i.numInstallation = :numInstallation"),
    @NamedQuery(name = "Installation.findByNomInstallation", query = "SELECT i FROM Installation i WHERE i.nomInstallation = :nomInstallation"),
    @NamedQuery(name = "Installation.findByNomEquipement", query = "SELECT i FROM Installation i WHERE i.nomEquipement = :nomEquipement"),
    @NamedQuery(name = "Installation.findByDepartement", query = "SELECT i FROM Installation i WHERE i.departement = :departement"),
    @NamedQuery(name = "Installation.findByCommune", query = "SELECT i FROM Installation i WHERE i.commune = :commune"),
    @NamedQuery(name = "Installation.findByGpsX", query = "SELECT i FROM Installation i WHERE i.gpsX = :gpsX"),
    @NamedQuery(name = "Installation.findByGpsY", query = "SELECT i FROM Installation i WHERE i.gpsY = :gpsY"),
    @NamedQuery(name = "Installation.findByHandicapM", query = "SELECT i FROM Installation i WHERE i.handicapM = :handicapM"),
    @NamedQuery(name = "Installation.findByHandicapS", query = "SELECT i FROM Installation i WHERE i.handicapS = :handicapS"),
    @NamedQuery(name = "Installation.findByLongueur", query = "SELECT i FROM Installation i WHERE i.longueur = :longueur"),
    @NamedQuery(name = "Installation.findByLargeur", query = "SELECT i FROM Installation i WHERE i.largeur = :largeur"),
    @NamedQuery(name = "Installation.findByHauteur", query = "SELECT i FROM Installation i WHERE i.hauteur = :hauteur"),
    @NamedQuery(name = "Installation.findByEclairage", query = "SELECT i FROM Installation i WHERE i.eclairage = :eclairage"),
    @NamedQuery(name = "Installation.findByAdresse", query = "SELECT i FROM Installation i WHERE i.adresse = :adresse"),
    @NamedQuery(name = "Installation.findAllCommune", query = "SELECT DISTINCT i.commune FROM Installation i"),
    @NamedQuery(name = "Installation.findByXXXXXXXXXXXXXXX", query = "SELECT i FROM Installation i, Installationactivite ia,  Activite a WHERE i.numInstallation = ia.installationactivitePK.numInstallation and ia.installationactivitePK.numActivite = a.numActivite and i.commune like :commune AND a.nomActivite like :nomActivite")})

public class Installation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "numInstallation")
    private String numInstallation;
    @Size(max = 250)
    @Column(name = "nomInstallation")
    private String nomInstallation;
    @Size(max = 250)
    @Column(name = "nomEquipement")
    private String nomEquipement;
    @Size(max = 250)
    @Column(name = "departement")
    private String departement;
    @Size(max = 250)
    @Column(name = "commune")
    private String commune;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "gpsX")
    private Float gpsX;
    @Column(name = "gpsY")
    private Float gpsY;
    @Column(name = "handicapM")
    private Boolean handicapM;
    @Column(name = "handicapS")
    private Boolean handicapS;
    @Column(name = "longueur")
    private Float longueur;
    @Column(name = "largeur")
    private Float largeur;
    @Column(name = "hauteur")
    private Float hauteur;
    @Column(name = "eclairage")
    private Boolean eclairage;
    @Size(max = 1000)
    @Column(name = "adresse")
    private String adresse;
    @OneToMany(mappedBy = "numInstallation")
    private List<Creneau> creneauList;
    @JoinColumn(name = "numUtilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur numUtilisateur;

    public Installation() {
    }

    public Installation(String numInstallation) {
        this.numInstallation = numInstallation;
    }

    public String getNumInstallation() {
        return numInstallation;
    }

    public void setNumInstallation(String numInstallation) {
        this.numInstallation = numInstallation;
    }

    public String getNomInstallation() {
        return nomInstallation;
    }

    public void setNomInstallation(String nomInstallation) {
        this.nomInstallation = nomInstallation;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public Float getGpsX() {
        return gpsX;
    }

    public void setGpsX(Float gpsX) {
        this.gpsX = gpsX;
    }

    public Float getGpsY() {
        return gpsY;
    }

    public void setGpsY(Float gpsY) {
        this.gpsY = gpsY;
    }

    public Boolean getHandicapM() {
        return handicapM;
    }

    public void setHandicapM(Boolean handicapM) {
        this.handicapM = handicapM;
    }

    public Boolean getHandicapS() {
        return handicapS;
    }

    public void setHandicapS(Boolean handicapS) {
        this.handicapS = handicapS;
    }

    public String getLongueur() {
        if (longueur == null) return "-";
        return longueur+" mètres";
    }

    public void setLongueur(Float longueur) {
        this.longueur = longueur;
    }

    public String getLargeur() {
        if (largeur==null) return "-";
        else return largeur+" mètres";
    }

    public void setLargeur(Float largeur) {
        this.largeur = largeur;
    }

    public String getHauteur() {
        if (hauteur==null) return "-";
        return hauteur+" mètres";
    }

    public void setHauteur(Float hauteur) {
        this.hauteur = hauteur;
    }

    public String getEclairage() {
        if (eclairage==false) return "non";
        return "0ui";
    }

    public void setEclairage(Boolean eclairage) {
        this.eclairage = eclairage;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlTransient
    public List<Creneau> getCreneauList() {
        return creneauList;
    }

    public void setCreneauList(List<Creneau> creneauList) {
        this.creneauList = creneauList;
    }

    public Utilisateur getNumUtilisateur() {
        return numUtilisateur;
    }

    public void setNumUtilisateur(Utilisateur numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numInstallation != null ? numInstallation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Installation)) {
            return false;
        }
        Installation other = (Installation) object;
        if ((this.numInstallation == null && other.numInstallation != null) || (this.numInstallation != null && !this.numInstallation.equals(other.numInstallation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Installation[ numInstallation=" + numInstallation + " ]";
    }
    
}
