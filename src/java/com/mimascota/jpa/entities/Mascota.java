/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m"),
    @NamedQuery(name = "Mascota.findByIdMascota", query = "SELECT m FROM Mascota m WHERE m.idMascota = :idMascota"),
    @NamedQuery(name = "Mascota.findByEspecie", query = "SELECT m FROM Mascota m WHERE m.especie = :especie"),
    @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mascota.findByGenero", query = "SELECT m FROM Mascota m WHERE m.genero = :genero"),
    @NamedQuery(name = "Mascota.findByRaza", query = "SELECT m FROM Mascota m WHERE m.raza = :raza"),
    @NamedQuery(name = "Mascota.findByColor", query = "SELECT m FROM Mascota m WHERE m.color = :color"),
    @NamedQuery(name = "Mascota.findByFechaNacimiento", query = "SELECT m FROM Mascota m WHERE m.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Mascota.findByPeso", query = "SELECT m FROM Mascota m WHERE m.peso = :peso"),
    @NamedQuery(name = "Mascota.findByObservaciones", query = "SELECT m FROM Mascota m WHERE m.observaciones = :observaciones")})
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mascota")
    private Integer idMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "especie")
    private String especie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "raza")
    private String raza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private float peso;
    @Size(max = 100)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota")
    private List<Factura> facturaList;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mascota")
    private List<VacunasHasMascota> vacunasHasMascotaList;

    public Mascota() {
    }

    public Mascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Mascota(Integer idMascota, String especie, String nombre, String genero, String raza, String color, Date fechaNacimiento, float peso) {
        this.idMascota = idMascota;
        this.especie = especie;
        this.nombre = nombre;
        this.genero = genero;
        this.raza = raza;
        this.color = color;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public List<VacunasHasMascota> getVacunasHasMascotaList() {
        return vacunasHasMascotaList;
    }

    public void setVacunasHasMascotaList(List<VacunasHasMascota> vacunasHasMascotaList) {
        this.vacunasHasMascotaList = vacunasHasMascotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.innovapps.almaiz.jpa.entities.Mascota[ idMascota=" + idMascota + " ]";
    }
    
}
