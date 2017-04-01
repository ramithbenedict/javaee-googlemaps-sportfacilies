/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Utilisateur;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Moms
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "com.mycompany_JEE2.0_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    
    public  Utilisateur trouver(String username, String password) {
        Utilisateur entity = new Utilisateur();
        entity.setEmail(username);
        entity.setMotDePasse(password);
        Query query = em.createNamedQuery("Utilisateur.findByEmailAndMotDePasse", Utilisateur.class);
        query.setParameter("email", username);
        query.setParameter("motDePasse", password);
        try {
            entity=(Utilisateur) query.getSingleResult();
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login / Mot de passe incorrect(s)", ""));
            entity = null;
        }
        return entity;
    }
    
    public  String CreateUser(String email, String mdp, String nom, String prenom, String numTel){
    
        Utilisateur entity = new Utilisateur();
      
        Query query = em.createNamedQuery("Utilisateur.findByEmail", Utilisateur.class);
        query.setParameter("email", email);
        try {
            entity=(Utilisateur) query.getSingleResult();
            FacesContext.getCurrentInstance().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email existant", ""));
            return "inscription";
        }
        catch(Exception e){
           // em.getTransaction().begin();
            entity.setEmail(email);
            entity.setMotDePasse(mdp);
            entity.setNom(nom);
            entity.setPrenom(prenom);
            entity.setTelephone(numTel);
            em.persist(entity);
           // em.getTransaction().commit();
            return "index?faces-redirect=true";
        }
        
            /*
                try{
                     em.persist(entity);
                     return "index?faces-redirect=true";
                }

                catch(Exception e){
                     FacesContext.getCurrentInstance().addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail existant", ""));
                }

               // em.getTransaction().commit();
                return null;
           */
    }
    
    public String updateUser(Utilisateur user, String email, String mdp, String nom, String prenom, String numTel){
            user.setEmail(email);
            user.setMotDePasse(mdp);
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setTelephone(numTel);
            em.merge(user);
            return "recherche?faces-redirect=true";
    }
}
