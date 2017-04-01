/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Activite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Moms
 */
@Stateless
public class ActiviteFacade extends AbstractFacade<Activite> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActiviteFacade() {
        super(Activite.class);
    }
    
     /*public  List<String> findAllNomActivite() {
        List<String> list ;
        Query query = em.createNamedQuery("Activite.findAllNomActivite", Activite.class);
     
        try {
            list=(List<String>) query.getResultList();
        }
        catch(Exception e){
            System.out.println("Aucune activité");
            list = null;
        }
        return list;
    }*/
    
}
