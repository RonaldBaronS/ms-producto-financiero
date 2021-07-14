package com.unmsm.producto.dao;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;

import java.util.Date;
import java.util.List;

public interface ProductoFinancieroDao {
    
    void save(DepositoPlazoFijoLima entity);
    void procesar(List<DepositoPlazoFijoLima> entitys);
    List<DepositoPlazoFijoLima> findAll();
    List<DepositoPlazoFijoLima> findByFecha(Date fechaInicio, Date fechaFin);
    //List<DepositoPlazoFijoLima> findByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin);
    
}
