package controller;

import model.InstallationFacade;
import entities.Installation;
import entities.Utilisateur;
import util.JsfUtil;
import util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@Named("installationController")
@ManagedBean
@SessionScoped
public class InstallationController implements Serializable {

    @EJB
    private model.InstallationFacade ejbFacade;
    private List<Installation> items = null;
    private Installation selected;

    /*******************************************************************************************/
    
        /* Valeurs entrées par l'utilisateur */
	
	private String activity ;
	private String place;
	private Date date;

	/* Variables utilisées en interne */
	
		// Variables liées à la carte
	
	private String centerGeoMap = "46.1281243, -2.2773417"; // Centrage sur la France entière
	private MapModel mapModel;
	
		// Résultats de la recherche
	
	private List<Installation> results;
        
        // Données de l'installation sélectionnée
        
        private Installation selectedInstalaltion;
        
        /* Méthodes */
	
        // Appelée au chargement de la page
        public void onLoad(Utilisateur utilisateur){
            search(utilisateur);
        }
        
	public void onGeocode(GeocodeEvent event) {
            List<GeocodeResult> results = event.getResults();

            if (results != null && !results.isEmpty()) {
                LatLng center = results.get(0).getLatLng();
                centerGeoMap = center.getLat() + "," + center.getLng();
            }
    }
	
	public void onMarkerSelect(OverlaySelectEvent event) {
            Marker selectedMarker = (Marker)event.getOverlay();
            
            // On initialise toutes les données de l'installation sélectionnée
            selectedInstalaltion = (Installation) selectedMarker.getData();
	}
        
	public void search(Utilisateur utilisateur){
            try{
                results = findAllXXX();
                if(results.isEmpty())
                    FacesContext.getCurrentInstance().addMessage("noresults", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun résultat", "Essayez de modifier vos critères de recherche."));
                
                Integer a = 1;
                ejbFacade.addHistorique(a, activity, place, utilisateur);
                
                mapModel = new DefaultMapModel();
                for(Installation result : results)
                    mapModel.addOverlay(new Marker(new LatLng(result.getGpsY(), result.getGpsX()), result.getNomInstallation(), result));
            }
            catch(Exception e){
                FacesContext.getCurrentInstance().addMessage("noresults", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun résultat", "Essayez de modifier vos critères de recherche."));
            }            
	}

	/* Getters et setters */
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public List<Installation> getResults() {
		return results;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
        
        public Installation getSelectedInstalaltion() {
            return selectedInstalaltion;
        }
        
        public void setSelectedInstalaltion(Installation selectedInstalaltion) {
           this.selectedInstalaltion = selectedInstalaltion;
        }
        
        
    /*******************************************************************************************/
        
    public InstallationController() {
    }

    public Installation getSelected() {
        return selected;
    }

    public void setSelected(Installation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InstallationFacade getFacade() {
        return ejbFacade;
    }

    public Installation prepareCreate() {
        selected = new Installation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InstallationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InstallationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InstallationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Installation> getItems() {
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

    public Installation getInstallation(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Installation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Installation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Installation.class)
    public static class InstallationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InstallationController controller = (InstallationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "installationController");
            return controller.getInstallation(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Installation) {
                Installation o = (Installation) object;
                return getStringKey(o.getNumInstallation());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Installation.class.getName()});
                return null;
            }
        }

    }
    
    public  List<Installation> findAllXXX() throws Exception {
        String activity;
        if (getActivity() == null)
            activity = "";
        else activity = getActivity();
        
        String place;
        if (getPlace() == null)
            place = "";
        else place = getPlace();
           
        List<Installation> list = ejbFacade.findAllXXX(activity, place);

        if (list == null)
            throw new Exception("Aucune activité correspondante");

        return list;
    
    }
    
    public List<String> findAllCommune(){
        
        List<String> list = ejbFacade.findAllCommune();

        return list;
    }

    public List<String> completePlace(String query) {
        List<String> results = new ArrayList<>();
        for(String commune : findAllCommune()){
            if(commune.toUpperCase().contains(query.toUpperCase()) || commune.toLowerCase().contains(query.toLowerCase()))
                results.add(commune);
        }
         
        return results;
    }
    
    public Integer moyenne(String numInstallation){
       return Math.round(ejbFacade.moyenne(numInstallation));
    }
}
