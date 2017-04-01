package controller;

import model.InstallationactiviteFacade;
import entities.Installationactivite;
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
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("installationactiviteController")
@ManagedBean
@SessionScoped
public class InstallationactiviteController implements Serializable {

    @EJB
    private model.InstallationactiviteFacade ejbFacade;
    private List<Installationactivite> items = null;
    private Installationactivite selected;

    public InstallationactiviteController() {
    }

    public Installationactivite getSelected() {
        return selected;
    }

    public void setSelected(Installationactivite selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setInstallationactivitePK(new entities.InstallationactivitePK());
    }

    private InstallationactiviteFacade getFacade() {
        return ejbFacade;
    }

    public Installationactivite prepareCreate() {
        selected = new Installationactivite();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstallationactiviteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstallationactiviteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstallationactiviteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Installationactivite> getItems() {
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

    public Installationactivite getInstallationactivite(entities.InstallationactivitePK id) {
        return getFacade().find(id);
    }

    public List<Installationactivite> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Installationactivite> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Installationactivite.class)
    public static class InstallationactiviteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstallationactiviteController controller = (InstallationactiviteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "installationactiviteController");
            return controller.getInstallationactivite(getKey(value));
        }

        entities.InstallationactivitePK getKey(String value) {
            entities.InstallationactivitePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.InstallationactivitePK();
            key.setNumInstallation(values[0]);
            key.setNumActivite(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entities.InstallationactivitePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getNumInstallation());
            sb.append(SEPARATOR);
            sb.append(value.getNumActivite());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Installationactivite) {
                Installationactivite o = (Installationactivite) object;
                return getStringKey(o.getInstallationactivitePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Installationactivite.class.getName()});
                return null;
            }
        }

    }

}
