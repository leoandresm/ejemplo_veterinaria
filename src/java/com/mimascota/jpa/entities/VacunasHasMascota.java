/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leoandresm
 */
@Entity
@Table(name = "vacunas_has_mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacunasHasMascota.findAll", query = "SELECT v FROM VacunasHasMascota v"),
    @NamedQuery(name = "VacunasHasMascota.findByVacunasIdVacunas", query = "SELECT v FROM VacunasHasMascota v WHERE v.vacunasHasMascotaPK.vacunasIdVacunas = :vacunasIdVacunas"),
    @NamedQuery(name = "VacunasHasMascota.findByMascotaIdMascota", query = "SELECT v FROM VacunasHasMascota v WHERE v.vacunasHasMascotaPK.mascotaIdMascota = :mascotaIdMascota"),
    @NamedQuery(name = "VacunasHasMascota.findByFechaAplicacion", query = "SELECT v FROM VacunasHasMascota v WHERE v.fechaAplicacion = :fechaAplicacion")})
public class VacunasHasMascota implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VacunasHasMascotaPK vacunasHasMascotaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_aplicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAplicacion;
    @JoinColumn(name = "mascota_id_mascota", referencedColumnName = "id_mascota", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mascota mascota;
    @JoinColumn(name = "vacunas_id_vacunas", referencedColumnName = "id_vacunas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vacunas vacunas;

    public VacunasHasMascota() {
    }

    public VacunasHasMascota(VacunasHasMascotaPK vacunasHasMascotaPK) {
        this.vacunasHasMascotaPK = vacunasHasMascotaPK;
    }

    public VacunasHasMascota(VacunasHasMascotaPK vacunasHasMascotaPK, Date fechaAplicacion) {
        this.vacunasHasMascotaPK = vacunasHasMascotaPK;
        this.fechaAplicacion = fechaAplicacion;
    }

    public VacunasHasMascota(short vacunasIdVacunas, int mascotaIdMascota) {
        this.vacunasHasMascotaPK = new VacunasHasMascotaPK(vacunasIdVacunas, mascotaIdMascota);
    }

    public VacunasHasMascotaPK getVacunasHasMascotaPK() {
        return vacunasHasMascotaPK;
    }

    public void setVacunasHasMascotaPK(VacunasHasMascotaPK vacunasHasMascotaPK) {
        this.vacunasHasMascotaPK = vacunasHasMascotaPK;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Vacunas getVacunas() {
        return vacunas;
    }

    public void setVacunas(Vacunas vacunas) {
        this.vacunas = vacunas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacunasHasMascotaPK != null ? vacunasHasMascotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacunasHasMascota)) {
            return false;
        }
        VacunasHasMascota other = (VacunasHasMascota) object;
        if ((this.vacunasHasMascotaPK == null && other.vacunasHasMascotaPK != null) || (this.vacunasHasMascotaPK != null && !this.vacunasHasMascotaPK.equals(other.vacunasHasMascotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.innovapps.almaiz.jpa.entities.VacunasHasMascota[ vacunasHasMascotaPK=" + vacunasHasMascotaPK + " ]";
    }
    
}
