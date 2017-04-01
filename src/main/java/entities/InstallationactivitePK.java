/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Moms
 */
@Embeddable
public class InstallationactivitePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "numInstallation")
    private String numInstallation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numActivite")
    private int numActivite;

    public InstallationactivitePK() {
    }

    public InstallationactivitePK(String numInstallation, int numActivite) {
        this.numInstallation = numInstallation;
        this.numActivite = numActivite;
    }

    public String getNumInstallation() {
        return numInstallation;
    }

    public void setNumInstallation(String numInstallation) {
        this.numInstallation = numInstallation;
    }

    public int getNumActivite() {
        return numActivite;
    }

    public void setNumActivite(int numActivite) {
        this.numActivite = numActivite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numInstallation != null ? numInstallation.hashCode() : 0);
        hash += (int) numActivite;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstallationactivitePK)) {
            return false;
        }
        InstallationactivitePK other = (InstallationactivitePK) object;
        if ((this.numInstallation == null && other.numInstallation != null) || (this.numInstallation != null && !this.numInstallation.equals(other.numInstallation))) {
            return false;
        }
        if (this.numActivite != other.numActivite) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InstallationactivitePK[ numInstallation=" + numInstallation + ", numActivite=" + numActivite + " ]";
    }
    
}
