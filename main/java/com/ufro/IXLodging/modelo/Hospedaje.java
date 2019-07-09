/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicole
 */
@Entity
@Table(name = "hospedaje")
@NamedQueries({
    @NamedQuery(name = "Hospedaje.findAll", query = "SELECT h FROM Hospedaje h")})
public class Hospedaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHospedaje")
    private Integer idHospedaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombreHospedaje")
    private String nombreHospedaje;
    @Basic(optional = true)
    @Size(min = 1, max = 50)
    @Column(name = "dueno")
    private String dueno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ubicacion")
    private String ubicacion;
    @JoinColumn(name = "id_Usuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHospedaje", fetch = FetchType.LAZY)
    private List<Pieza> piezaList;

    public Hospedaje() {
    }

    public Hospedaje(Integer idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public Hospedaje(Integer idHospedaje, String nombreHospedaje, String dueno, String descripcion, String ubicacion) {
        this.idHospedaje = idHospedaje;
        this.nombreHospedaje = nombreHospedaje;
        this.dueno = dueno;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public Hospedaje(String nombreHospedaje, String descripcion, String ubicacion) {
        this.nombreHospedaje = nombreHospedaje;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public Integer getIdHospedaje() {
        return idHospedaje;
    }

    public void setIdHospedaje(Integer idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public String getNombreHospedaje() {
        return nombreHospedaje;
    }

    public void setNombreHospedaje(String nombreHospedaje) {
        this.nombreHospedaje = nombreHospedaje;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHospedaje != null ? idHospedaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospedaje)) {
            return false;
        }
        Hospedaje other = (Hospedaje) object;
        if ((this.idHospedaje == null && other.idHospedaje != null) || (this.idHospedaje != null && !this.idHospedaje.equals(other.idHospedaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufro.IXLodging.modelo.Hospedaje[ idHospedaje=" + idHospedaje + " ]";
    }
    
}
