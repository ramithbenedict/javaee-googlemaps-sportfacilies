/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Historique;
import entities.Installation;
import entities.Utilisateur;
import java.time.Instant;
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
public class HistoriqueFacade extends AbstractFacade<Historique> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriqueFacade() {
        super(Historique.class);
    }
/*
    public Historique addHistorique(Integer numHistorique, String activite, String lieuRecherche, Utilisateur numUtilisateur) {
        System.out.println("Facade");
        
        Historique entity = new Historique();
        Date dateRecherche = Date.from(Instant.now());
        entity.setNumHistorique(numHistorique);
        entity.setActivite(activite);
        entity.setDateRecherche(dateRecherche);
        entity.setLieuRecherche(lieuRecherche);
        entity.setNumUtilisateur(numUtilisateur);
        em.persist(entity);
        System.out.println("fin de creation");
        return entity;
    }
*/
    public List<Historique> findHistoriqueByNumUtilisateur(Utilisateur numUtilisateur) {

        List<Historique> list;
        Query query = em.createNamedQuery("Historique.findByNumUtilisateur", Historique.class);
        query.setParameter("numUtilisateur", numUtilisateur);
        list = (List<Historique>) query.getResultList();
        return list;
    }
    public void  removeAll (Utilisateur utilisateur){
        Query query = em.createNamedQuery("Historique.removeAll", Historique.class);
        query.setParameter("numUtilisateur", utilisateur);
        query.executeUpdate();
    }
}
