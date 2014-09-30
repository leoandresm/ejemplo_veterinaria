/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jpa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "vacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacunas.findAll", query = "SELECT v FROM Vacunas v"),
    @NamedQuery(name = "Vacunas.findByIdVacunas", query = "SELECT v FROM Vacunas v WHERE v.idVacunas = :idVacunas"),
    @NamedQuery(name = "Vacunas.findByNombreVacuna", query = "SELECT v FROM Vacunas v WHERE v.nombreVacuna = :nombreVacuna")})
public class Vacunas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vacunas")
    private Short idVacunas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_vacuna")
    private String nombreVacuna;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacunas")
    private List<VacunasHasMascota> vacunasHasMascotaList;

    public Vacunas() {
    }

    public Vacunas(Short idVacunas) {
        this.idVacunas = idVacunas;
    }

    public Vacunas(Short idVacunas, String nombreVacuna) {
        this.idVacunas = idVacunas;
        this.nombreVacuna = nombreVacuna;
    }

    public Short getIdVacunas() {
        return idVacunas;
    }

    public void setIdVacunas(Short idVacunas) {
        this.idVacunas = idVacunas;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
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
        hash += (idVacunas != null ? idVacunas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacunas)) {
            return false;
        }
        Vacunas other = (Vacunas) object;
        if ((this.idVacunas == null && other.idVacunas != null) || (this.idVacunas != null && !this.idVacunas.equals(other.idVacunas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.innovapps.almaiz.jpa.entities.Vacunas[ idVacunas=" + idVacunas + " ]";
    }
    
}
