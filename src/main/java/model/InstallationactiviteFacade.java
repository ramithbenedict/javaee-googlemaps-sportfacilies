/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Installationactivite;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Moms
 */
@Stateless
public class InstallationactiviteFacade extends AbstractFacade<Installationactivite> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstallationactiviteFacade() {
        super(Installationactivite.class);
    }
    
}
