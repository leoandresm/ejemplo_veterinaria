/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jsf.controller;

import com.mimascota.jpa.entities.Cliente;
import com.mimascota.jpa.sessions.ClienteFacade;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private String username;
    private String password;
    private String rol = "";
    private Cliente usuario;
    @EJB
    private ClienteFacade clienteFacade;

    public LoginController() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public String getUsername() {
        return username;
    }

    private ClienteFacade getUsuarioFacade() {
        return clienteFacade;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return getRequest().getUserPrincipal() != null;
    }

    public Principal getPrincipal() {
        return getRequest().getUserPrincipal();
    }

    private HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Object request = externalContext.getRequest();
        return request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
    }

    private String getLogueado() {
        return getPrincipal().getName();
    }
    
//    public Cliente getUserLogueado() {
//        return getUsuarioFacade().findByDocumento(getLogueado());
//    }

//    public String getRol() {
//        if (getRequest().isUserInRole("webAdmin")) {
//            rol = "Administrador";
//        } else if (getRequest().isUserInRole("webDoctor")) {
//            rol = "Médico";
//        } else if (getRequest().isUserInRole("webNurse")) {
//            rol = "Enfermer@";
//        } else if (getRequest().isUserInRole("webUser")) {
//            rol = "Usuario";
//        }
//        return rol;
//    }

//    public boolean isAdministrador() {
//        return getRequest().isUserInRole("webAdmin");
//    }
//
//    public boolean isDoctor() {
//        return getRequest().isUserInRole("webDoctor");
//    }
//
//    public boolean isNurse() {
//        return getRequest().isUserInRole("webNurse");
//    }
//
//    public boolean isUser() {
//        return getRequest().isUserInRole("webUser");
//    }

    public String login() {
        try {
            //Login via the Servlet Context
            getRequest().login(username, password);

//            usuario = getUserLogueado();
            limpiar();

//            //Cancela login para usuarios inactivos
//            if (!usuario.getEstado()) {
//                logout();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Inactivo", null));
//                return "/index";
//            }
            //Redirigir a la página de portada
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ " + usuario.toString(), null));
            return "/user/user_index";

        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Invalida", null));
            return "/index";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            limpiar();
            return "/index";
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            return "/user/user_index";
        }
    }

    private void limpiar() {
        username = "";
        password = "";
    }
}
