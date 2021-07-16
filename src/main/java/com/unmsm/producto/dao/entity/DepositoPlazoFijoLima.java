package com.unmsm.producto.dao.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long id;
    
    @Column(name="entidad",length=50)
    private String entidad;
    
    @Column(name="tasa",length=50)
    private String tasa;
    
    @Column(name="fecha")
    private LocalDate fecha;
    
}