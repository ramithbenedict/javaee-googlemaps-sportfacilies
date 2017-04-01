/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Creneau;
import entities.Installation;
import entities.Utilisateur;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
public class CreneauFacade extends AbstractFacade<Creneau> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CreneauFacade() {
        super(Creneau.class);
    }
    
    public List<Creneau> findAllCreneauByNumInstallation(String numInstallation){
        
        List<Creneau> list;
        Date date = Date.from(Instant.now());
        Query query = em.createNamedQuery("Creneau.findByNumInstallation", Creneau.class);
        query.setParameter("numInstallation", numInstallation);
        query.setParameter("date", date);
        list=(List<Creneau>) query.getResultList();
        return list;
    }
    
    public List<Creneau> findAllyCreneauByNumUtilisateur(Utilisateur utilisateur){
         
        List<Creneau> list;
        Query query = em.createNamedQuery("Creneau.findByNumUtilisateur", Creneau.class);
        query.setParameter("numUtilisateur", utilisateur);
        list=(List<Creneau>) query.getResultList();
        return list;
    }

     public void reservation (Creneau creneau, Integer numUtilisateur){

        Utilisateur user = em.createNamedQuery("Utilisateur.findByNumUtilisateur", Utilisateur.class).setParameter("numUtilisateur", numUtilisateur).getSingleResult(); 
        creneau.setNumUtilisateur(user);
        creneau.setDisponible(false);
        em.merge(creneau);
     }
     
     public void commentaire (Creneau creneau, int note, String commentaire){
         creneau.setCommentaire(commentaire);
         creneau.setNote(note);
         em.merge(creneau);
     }
     /*
     public List<String> findAllCommentaire(String numInstallation){
         
        Installation inst;
        Query query = em.createNamedQuery("Installation.findByNumInstallation", Installation.class);
        query.setParameter("numInstallation", numInstallation);
        inst = (Installation) query.getSingleResult();

        List<String> list = new ArrayList<>();

        for (Creneau c : inst.getCreneauList()) {
            String commentaire = c.getCommentaire();
            if ( commentaire != null) {
                list.add(commentaire);
            }
        }
        
        return list;
     }*/
     
     public List<Creneau> findAllCommentaire(String numInstallation){
        
        List<Creneau> list;
        Query query = em.createNamedQuery("Creneau.findByNumInstallationCom", Creneau.class);
        query.setParameter("numInstallation", numInstallation);
        list=(List<Creneau>) query.getResultList();
        return list;
    }
}
