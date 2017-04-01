package controller;

import entities.Historique;
import entities.Utilisateur;
import model.HistoriqueFacade;
import util.JsfUtil;
import util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("historiqueController")
@ManagedBean
@SessionScoped
public class HistoriqueController implements Serializable {

    @EJB
    private model.HistoriqueFacade ejbFacade;
    private List<Historique> items = null;
    private Historique selected;

    public HistoriqueController() {
    }

    public Historique getSelected() {
        return selected;
    }

    public void setSelected(Historique selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HistoriqueFacade getFacade() {
        return ejbFacade;
    }

    public Historique prepareCreate() {
        selected = new Historique();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HistoriqueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HistoriqueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HistoriqueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Historique> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Historique getHistorique(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Historique> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Historique> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Historique.class)
    public static class HistoriqueControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HistoriqueController controller = (HistoriqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "historiqueController");
            return controller.getHistorique(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Historique) {
                Historique o = (Historique) object;
                return getStringKey(o.getNumHistorique());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Historique.class.getName()});
                return null;
            }
        }

    }

    /* Valeurs entrées par l'utilisateur */
    private Integer numHistorique;
    private String activite;
    private String lieuRecherche;
    private Utilisateur numUtilisateur;

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
    
    
    
    
    
    
    
   /**************************************************/
/*
    public void addHistorique() {
        System.out.println("Controller");
        
        //Historique hist =  ejbFacade.addHistorique(selected.getNumHistorique(), selected.getActivite(), selected.getDateRecherche(), selected.getLieuRecherche(), selected.getNumUtilisateur());
        Integer a = 1;
        
        Historique hist =  ejbFacade.addHistorique(a, activite, lieuRecherche, numUtilisateur);
        
        if(hist == null){
            System.out.println("hist null ");
            
        }         
    }
*/
    public List<Historique> findHistoriqueByNumUtilisateur(Utilisateur numUtilisateur) {
        items = ejbFacade.findHistoriqueByNumUtilisateur(numUtilisateur);
        return ejbFacade.findHistoriqueByNumUtilisateur(numUtilisateur);
    }
    public void remove (Historique h){
        ejbFacade.remove(h);
        FacesContext.getCurrentInstance().addMessage("suppression", new FacesMessage(FacesMessage.SEVERITY_INFO, "Elément supprimé", ""));
    }
    public void removeAll (Utilisateur utilisateur){
        ejbFacade.removeAll(utilisateur);
         FacesContext.getCurrentInstance().addMessage("suppression", new FacesMessage(FacesMessage.SEVERITY_INFO, "Historique supprimé", "Tout le contenu de votre historique a été supprimé avec succès"));
    }
   }
