/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jsf.controller;

import com.mimascota.jpa.entities.Ciudad;
import com.mimascota.jpa.entities.Cliente;
import com.mimascota.jpa.entities.Genero;
import com.mimascota.jpa.entities.TipoDocumento;
import com.mimascota.jpa.sessions.CiudadFacade;
import com.mimascota.jpa.sessions.ClienteFacade;
import com.mimascota.jpa.sessions.GeneroFacade;
import com.mimascota.jpa.sessions.TipoDocumentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author leoandresm
 */
@Named(value = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
   
    private Cliente clienteActual;
    private List<Cliente> listaClientes = null;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @EJB
    private GeneroFacade generoFacade;
    @EJB
    private TipoDocumentoFacade tipoDocumentoFacade;
    
    
    
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
    
    public List<Ciudad> getListCiudadSelectOne() {
        return getCiudadFacade().findAll();
    }
    
    public List<Genero> getListGeneroSelectOne() {
        return getGeneroFacade().findAll();
    }
    
    public List<TipoDocumento> getListTipoDocumentoSelectOne() {
        return getTipoDocumentoFacade().findAll();
    }

    private void recargarLista() {
        listaClientes = null;
    }

    public String prepareCreate() {
        clienteActual = new Cliente();
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
