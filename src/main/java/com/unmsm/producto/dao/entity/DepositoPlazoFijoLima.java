package com.unmsm.producto.dao.entity;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="SBSDepoPlazFijLima")
@Getter
@Setter
public class DepositoPlazoFijoLima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="entidad",length=50)
    private String entidad;
    
    @Column(name="tasa",length=50)
    private String tasa;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=ISO.DATE)
    @Column(name="fecha")
    //@NotNull
    private Date fecha;
    
}