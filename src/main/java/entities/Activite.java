/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moms
 */
@Entity
@Table(name = "activite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activite.findAll", query = "SELECT a FROM Activite a"),
    @NamedQuery(name = "Activite.findAllNomActivite", query = "SELECT a.nomActivite FROM Activite a"),
    @NamedQuery(name = "Activite.findByNumActivite", query = "SELECT a FROM Activite a WHERE a.numActivite = :numActivite"),
    @NamedQuery(name = "Activite.findByNomActivite", query = "SELECT a FROM Activite a WHERE a.nomActivite = :nomActivite")})
public class Activite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numActivite")
    private Integer numActivite;
    @Size(max = 250)
    @Column(name = "nomActivite")
    private String nomActivite;

    public Activite() {
    }

    public Activite(Integer numActivite) {
        this.numActivite = numActivite;
    }

    public Integer getNumActivite() {
        return numActivite;
    }

    public void setNumActivite(Integer numActivite) {
        this.numActivite = numActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numActivite != null ? numActivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.numActivite == null && other.numActivite != null) || (this.numActivite != null && !this.numActivite.equals(other.numActivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Activite[ numActivite=" + numActivite + " ]";
    }
    
}
