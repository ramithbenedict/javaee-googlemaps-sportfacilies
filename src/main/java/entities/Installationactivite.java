/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Moms
 */
@Entity
@Table(name = "installationactivite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Installationactivite.findAll", query = "SELECT i FROM Installationactivite i"),
    @NamedQuery(name = "Installationactivite.findByNumInstallation", query = "SELECT i FROM Installationactivite i WHERE i.installationactivitePK.numInstallation = :numInstallation"),
    @NamedQuery(name = "Installationactivite.findByNumActivite", query = "SELECT i FROM Installationactivite i WHERE i.installationactivitePK.numActivite = :numActivite")})
public class Installationactivite implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InstallationactivitePK installationactivitePK;

    public Installationactivite() {
    }

    public Installationactivite(InstallationactivitePK installationactivitePK) {
        this.installationactivitePK = installationactivitePK;
    }

    public Installationactivite(String numInstallation, int numActivite) {
        this.installationactivitePK = new InstallationactivitePK(numInstallation, numActivite);
    }

    public InstallationactivitePK getInstallationactivitePK() {
        return installationactivitePK;
    }

    public void setInstallationactivitePK(InstallationactivitePK installationactivitePK) {
        this.installationactivitePK = installationactivitePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (installationactivitePK != null ? installationactivitePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Installationactivite)) {
            return false;
        }
        Installationactivite other = (Installationactivite) object;
        if ((this.installationactivitePK == null && other.installationactivitePK != null) || (this.installationactivitePK != null && !this.installationactivitePK.equals(other.installationactivitePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Installationactivite[ installationactivitePK=" + installationactivitePK + " ]";
    }
    
}
