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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moms
 */
@Entity
@Table(name = "historique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historique.findAll", query = "SELECT h FROM Historique h"),
    @NamedQuery(name = "Historique.findByNumHistorique", query = "SELECT h FROM Historique h WHERE h.numHistorique = :numHistorique"),
    @NamedQuery(name = "Historique.findByActivite", query = "SELECT h FROM Historique h WHERE h.activite = :activite"),
    @NamedQuery(name = "Historique.findByDateRecherche", query = "SELECT h FROM Historique h WHERE h.dateRecherche = :dateRecherche"),
    @NamedQuery(name = "Historique.findByNumUtilisateur", query = "SELECT h FROM Historique h WHERE h.numUtilisateur = :numUtilisateur"),
    @NamedQuery(name = "Historique.findByLieuRecherche", query = "SELECT h FROM Historique h WHERE h.lieuRecherche = :lieuRecherche"),
    @NamedQuery(name = "Historique.removeAll", query = "DELETE FROM Historique h WHERE h.numUtilisateur = :numUtilisateur ")})

public class Historique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numHistorique")
    private Integer numHistorique;
    @Size(max = 250)
    @Column(name = "activite")
    private String activite;
    @Column(name = "dateRecherche")
    @Temporal(TemporalType.DATE)
    private Date dateRecherche;
    @Size(max = 250)
    @Column(name = "lieuRecherche")
    private String lieuRecherche;
    @JoinColumn(name = "numUtilisateur", referencedColumnName = "numUtilisateur")
    @ManyToOne
    private Utilisateur numUtilisateur;

    public Historique() {
    }

    public Historique(Integer numHistorique) {
        this.numHistorique = numHistorique;
    }

    public Integer getNumHistorique() {
        return numHistorique;
    }

    public void setNumHistorique(Integer numHistorique) {
        this.numHistorique = numHistorique;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public Date getDateRecherche() {
        return dateRecherche;
    }

    public void setDateRecherche(Date dateRecherche) {
        this.dateRecherche = dateRecherche;
    }

    public String getLieuRecherche() {
        return lieuRecherche;
    }

    public void setLieuRecherche(String lieuRecherche) {
        this.lieuRecherche = lieuRecherche;
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
        hash += (numHistorique != null ? numHistorique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historique)) {
            return false;
        }
        Historique other = (Historique) object;
        if ((this.numHistorique == null && other.numHistorique != null) || (this.numHistorique != null && !this.numHistorique.equals(other.numHistorique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Historique[ numHistorique=" + numHistorique + " ]";
    }
    
}
