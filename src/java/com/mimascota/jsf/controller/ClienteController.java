/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jsf.controller;

import com.mimascota.jpa.entities.Ciudad;
import com.mimascota.jpa.entities.Cliente;
import com.mimascota.jpa.entities.Genero;
import com.mimascota.jpa.entities.Rol;
import com.mimascota.jpa.entities.TipoDocumento;
import com.mimascota.jpa.sessions.CiudadFacade;
import com.mimascota.jpa.sessions.ClienteFacade;
import com.mimascota.jpa.sessions.GeneroFacade;
import com.mimascota.jpa.sessions.RolFacade;
import com.mimascota.jpa.sessions.TipoDocumentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author leoandresm
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
   
    private Cliente clienteActual;
    private List<Cliente> listaClientes = null;
    private List<Rol> listaRoles = null;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @EJB
    private GeneroFacade generoFacade;
    @EJB
    private TipoDocumentoFacade tipoDocumentoFacade;
    @EJB
    private RolFacade rolFacade;
    
    
    
    /**
     * Creates a new instance of ClienteController
     */
    public ClienteController() {
    }

    public Cliente getClienteActual() {
        if (clienteActual == null) {
            clienteActual = new Cliente();
        }
        return clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public CiudadFacade getCiudadFacade() {
        return ciudadFacade;
    }

    public GeneroFacade getGeneroFacade() {
        return generoFacade;
    }

    public TipoDocumentoFacade getTipoDocumentoFacade() {
        return tipoDocumentoFacade;
    }

    public RolFacade getRolFacade() {
        return rolFacade;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    public List<Cliente> getListaClientes() {
        if (listaClientes == null) {
            try {
                listaClientes = getClienteFacade().findAll();
            } catch (Exception e) {
                addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
           }
        }
        return listaClientes;
    }
        
    public List<Genero> getListGeneroSelectOne() {
        return getGeneroFacade().findAll();
    }
    
    public List<TipoDocumento> getListTipoDocumentoSelectOne() {
        return getTipoDocumentoFacade().findAll();
    }
    
    public List<Rol> getListRolSelectOne() {
        return getRolFacade().findAll();
    }
    
    public List<Ciudad> getListCiudadesAutoComplete(String query) {
        try {
            return getCiudadFacade().findByNombre(query);
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

    private void recargarLista() {
        listaClientes = null;
    }

    public String prepareCreate() {
        clienteActual = new Cliente();
        listaRoles = new ArrayList<>();
        return "Create";
    }

    public String prepareEdit() {
        return "Edit";
    }

    public String prepareView() {
        return "View";
    }

    public String prepareList() {
        recargarLista();
        return "/cliente/List";
    }

    public String addCliente() {
        try {
            clienteActual.setFechaCreacionCliente(new Date());
            clienteActual.setEstado(true);
            clienteActual.setRolList(listaRoles);
            getClienteFacade().create(clienteActual);
            addSuccessMessage("Crear Cliente", "Cliente Creado Exitosamente");
            recargarLista();
            return "View";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String updateCliente() {
        try {
            getClienteFacade().edit(clienteActual);
            addSuccessMessage("Actualizar Cliente", "Cliente Actualizado Exitosamente");
            recargarLista();
            return "View";
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
            return null;
        }
    }

    public String deleteCliente() {
        try {
            getClienteFacade().remove(clienteActual);
            addSuccessMessage("Eliminar Cliente", "Cliente Eliminado Exitosamente");
            recargarLista();
        } catch (Exception e) {
            addErrorMessage("Error closing resource " + e.getClass().getName(), "Message: " + e.getMessage());
        }
        return "List";
    }
    
     public void validarDocumento(FacesContext contex, UIComponent component, Object value)
            throws ValidatorException {
        Cliente clienteConsulta = getClienteFacade().findByDocumento((String) value);

        if (clienteConsulta != null) {
            if (clienteActual.getIdCliente() != null) {
                if (!Objects.equals(clienteActual.getIdCliente(), clienteConsulta.getIdCliente())) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumentoTitle"),
                            ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumento")));
                }
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumentoTitle"),
                        ResourceBundle.getBundle("/resources/Bundle").getString("ValidatorDocumento")));
            }
        } else {
            String documento = (String) value;
            clienteActual.setNumeroDocumento(documento);
        }
    }

    private void addErrorMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    private void addSuccessMessage(String title, String msg) {
        FacesMessage facesMsg
                = new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    
    
    
    
}
