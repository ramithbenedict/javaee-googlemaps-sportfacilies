/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Creneau;
import entities.Historique;
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
public class InstallationFacade extends AbstractFacade<Installation> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstallationFacade() {
        super(Installation.class);
    }

    public List<Installation> findAllXXX(String nomActivite, String nomCommune) {

        String commune = nomCommune.concat("%");
        String activite;

        if (nomActivite.equals("") || nomActivite.isEmpty() || nomActivite == null) {
            activite = "%";
        } else {
            activite = nomActivite.concat("%");
        }

        List<Installation> list;
        Query query = em.createNamedQuery("Installation.findByXXXXXXXXXXXXXXX", Installation.class);
        query.setParameter("nomActivite", activite);
        query.setParameter("commune", commune);

        list = (List<Installation>) query.getResultList();

        return list;
    }

    public List<String> findAllCommune() {

        List<String> list;
        Query query = em.createNamedQuery("Installation.findAllCommune", Installation.class);
        list = (List<String>) query.getResultList();
        return list;
    }

    public void addHistorique(Integer numHistorique, String activite, String lieuRecherche, Utilisateur numUtilisateur) {
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
    }

    public float moyenne(String numInstallation) {

        Installation inst;
        Query query = em.createNamedQuery("Installation.findByNumInstallation", Installation.class);
        query.setParameter("numInstallation", numInstallation);
        inst = (Installation) query.getSingleResult();

        float moyenne = 0;
        int compteur = 0;

        for (Creneau c : inst.getCreneauList()) {
            int nb = c.getNote();
            if (nb > 0 && nb <= 10) {
                moyenne += nb;
                compteur++;
            }
        }

        if (compteur > 0) {
            moyenne = moyenne / compteur;
        }
        
        return moyenne;
    }
}
