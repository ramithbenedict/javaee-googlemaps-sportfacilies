package controller;

import model.CreneauFacade;
import entities.Creneau;
import entities.Installation;
import entities.Utilisateur;
import util.JsfUtil;
import util.JsfUtil.PersistAction;
import java.io.Serializable;
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

@Named("creneauController")
@ManagedBean
@SessionScoped
public class CreneauController implements Serializable {

    @EJB
    private model.CreneauFacade ejbFacade;
    private List<Creneau> items = null;
    private Creneau selected;

    
    
    /*Variable saisi par l'utilisateur*/
    private Installation selectedInstallation;
    private List<Creneau> creneaux;
    private Creneau selectedCreneau;
    private Integer note;
    private String commentaire;
    
    
    public CreneauController() {
    }

    public Creneau getSelected() {
        return selected;
    }

    public void setSelected(Creneau selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CreneauFacade getFacade() {
        return ejbFacade;
    }
    
      // Getters and setters saisi
     
    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
     public List<Creneau> getCreneaux() {
        return creneaux;
    }
     
    public Installation getSelectedInstallation() {
        return selectedInstallation;
    }

    public void setSelectedInstallation(Installation selectedInstallation) {
        this.selectedInstallation = selectedInstallation;
    }
    
    public Creneau getSelectedCreneau() {
        return selectedCreneau;
    }

    public void setSelectedCreneau(Creneau selectedCreneau) {
        this.selectedCreneau = selectedCreneau;
    }   

    public Creneau prepareCreate() {
        selected = new Creneau();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CreneauCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CreneauUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CreneauDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Creneau> getItems() {
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

    public Creneau getCreneau(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Creneau> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Creneau> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Creneau.class)
    public static class CreneauControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CreneauController controller = (CreneauController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "creneauController");
            return controller.getCreneau(getKey(value));
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
            if (object instanceof Creneau) {
                Creneau o = (Creneau) object;
                return getStringKey(o.getNumCreneau());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Creneau.class.getName()});
                return null;
            }
        }

    }
      
    /*****************************************************************************************************************************/
    

     public void initCreneaux(){         
         creneaux = ejbFacade.findAllCreneauByNumInstallation(selectedInstallation.getNumInstallation());
     }
         
    public List<Creneau> findAllCreneauByNumInstallation(String numInstallation){
         
         List<Creneau> list = ejbFacade.findAllCreneauByNumInstallation(numInstallation);
         return list;

     }
     
    public List<Creneau> findAllyCreneauByNumUtilisateur(Utilisateur utilisateur){
         
        List<Creneau> list = ejbFacade.findAllyCreneauByNumUtilisateur(utilisateur);
        return list;
    }
     


     public String reservation (Integer numUtilisateur ){
       
         ejbFacade.reservation(selectedCreneau,numUtilisateur);
         
         FacesContext.getCurrentInstance().addMessage("bookingConf", new FacesMessage(FacesMessage.SEVERITY_INFO, "Réservation confirmée", selectedCreneau.getNumInstallation().getNomInstallation() + "\n" + selectedCreneau.getDateCreneau() + " " + selectedCreneau.getHeure() + "h"));
         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         return "recherche?faces-redirect=true";
     }
    
    public String commentaire (Creneau creneau, int note, String commentaire){
        ejbFacade.commentaire(creneau, note, commentaire);
        return "recherche?faces-redirect=true";
    }
    
    public List<Creneau> findAllCommentaire(String numInstallation){
        return ejbFacade.findAllCommentaire(numInstallation);
    }
}
