/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mimascota.jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leoandresm
 */
@Embeddable
public class VacunasHasMascotaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "vacunas_id_vacunas")
    private short vacunasIdVacunas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mascota_id_mascota")
    private int mascotaIdMascota;

    public VacunasHasMascotaPK() {
    }

    public VacunasHasMascotaPK(short vacunasIdVacunas, int mascotaIdMascota) {
        this.vacunasIdVacunas = vacunasIdVacunas;
        this.mascotaIdMascota = mascotaIdMascota;
    }

    public short getVacunasIdVacunas() {
        return vacunasIdVacunas;
    }

    public void setVacunasIdVacunas(short vacunasIdVacunas) {
        this.vacunasIdVacunas = vacunasIdVacunas;
    }

    public int getMascotaIdMascota() {
        return mascotaIdMascota;
    }

    public void setMascotaIdMascota(int mascotaIdMascota) {
        this.mascotaIdMascota = mascotaIdMascota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vacunasIdVacunas;
        hash += (int) mascotaIdMascota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacunasHasMascotaPK)) {
            return false;
        }
        VacunasHasMascotaPK other = (VacunasHasMascotaPK) object;
        if (this.vacunasIdVacunas != other.vacunasIdVacunas) {
            return false;
        }
        if (this.mascotaIdMascota != other.mascotaIdMascota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.innovapps.almaiz.jpa.entities.VacunasHasMascotaPK[ vacunasIdVacunas=" + vacunasIdVacunas + ", mascotaIdMascota=" + mascotaIdMascota + " ]";
    }
    
}
