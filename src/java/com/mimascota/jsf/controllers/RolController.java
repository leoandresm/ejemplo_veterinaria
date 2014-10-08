/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jsf.controllers;

import com.mimascota.jpa.entities.Rol;
import com.mimascota.jpa.sessions.RolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leoandresm
 */
@Named(value = "rolController")
@SessionScoped
public class RolController implements Serializable {
    
    @EJB
    private RolFacade rolFacade;

    /**
     * Creates a new instance of RolController
     */
    public RolController() {
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }
    public Rol getRol(java.lang.String id) {
        return getRolFacade().find(id);
    }

    @FacesConverter(forClass = Rol.class, value = "rolConverter")
    public static class RolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolController controller = (RolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolController");
            return controller.getRol(getKey(value));
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
            if (object instanceof Rol) {
                Rol o = (Rol) object;
                return getStringKey(o.getIdRol());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Rol.class.getName());
            }
        }

    }

}
