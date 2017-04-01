/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moms
 */
@Entity
@Table(name = "creneau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creneau.findAll", query = "SELECT c FROM Creneau c"),
    @NamedQuery(name = "Creneau.findByNumCreneau", query = "SELECT c FROM Creneau c WHERE c.numCreneau = :numCreneau"),
    @NamedQuery(name = "Creneau.findByDateCreneau", query = "SELECT c FROM Creneau c WHERE c.dateCreneau = :dateCreneau"),
    @NamedQuery(name = "Creneau.findByHeure", query = "SELECT c FROM Creneau c WHERE c.heure = :heure"),
    @NamedQuery(name = "Creneau.findByNumUtilisateur", query = "SELECT c FROM Creneau c WHERE c.numUtilisateur = :numUtilisateur"),
    @NamedQuery(name = "Creneau.findByNumInstallation", query = "SELECT c FROM Creneau c WHERE c.numInstallation.numInstallation = :numInstallation and c.dateCreneau > :date and c.disponible = true"),
    @NamedQuery(name = "Creneau.findByNumInstallationCom", query = "SELECT c FROM Creneau c WHERE c.numInstallation.numInstallation = :numInstallation and c.commentaire is not null"),
    @NamedQuery(name = "Creneau.findByNumInstallationAndHeure", query = "SELECT c FROM Creneau c, Installation i WHERE c.numInstallation.numInstallation = i.numInstallation and i.numInstallation = :numInstallation and c.heure = :heure and c.dateCreneau = :dateCreneau"),
    @NamedQuery(name = "Creneau.findByPrix", query = "SELECT c FROM Creneau c WHERE c.prix = :prix")})
//, Installation i WHERE c.numInstallation.numInstallation = i.numInstallation and i.numInstallation = :numInstallation
public class Creneau implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "disponible")
    private boolean disponible;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numCreneau")
    private Integer numCreneau;
    @Column(name = "dateCreneau")
    @Temporal(TemporalType.DATE)
    private Date dateCreneau;
    @Size(max = 200)
    @Column(name = "heure")
    private String heure;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Float prix;
    @JoinColumn(name = "numInstallation", referencedColumnName = "numInstallation")
    @ManyToOne
    private Installation numInstallation;
    @JoinColumn(name = "numUtilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur numUtilisateur;
    @Size(max = 1000)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "note")
    private int note;

    
    public Creneau() {
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean bool) {
        this.disponible = bool;
    }
    public Creneau(Integer numCreneau) {
        this.numCreneau = numCreneau;
    }

    public Integer getNumCreneau() {
        return numCreneau;
    }
    
    public void setNumCreneau(Integer numCreneau) {
        this.numCreneau = numCreneau;
    }

    public Date getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(Date dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Installation getNumInstallation() {
        return numInstallation;
    }

    public void setNumInstallation(Installation numInstallation) {
        this.numInstallation = numInstallation;
    }

    public Utilisateur getNumUtilisateur() {
        return numUtilisateur;
    }

    public void setNumUtilisateur(Utilisateur numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }
    
       public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCreneau != null ? numCreneau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creneau)) {
            return false;
        }
        Creneau other = (Creneau) object;
        if ((this.numCreneau == null && other.numCreneau != null) || (this.numCreneau != null && !this.numCreneau.equals(other.numCreneau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Creneau[ numCreneau=" + numCreneau + " ]";
    }

    public boolean getDisponible() {
        return disponible;
    } 
}
