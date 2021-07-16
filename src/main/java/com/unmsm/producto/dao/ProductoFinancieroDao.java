package com.unmsm.producto.dao;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import java.text.ParseException;
import java.util.List;

public interface ProductoFinancieroDao {
    
    void save(DepositoPlazoFijoLima entity);
    void procesar(List<DepositoPlazoFijoLima> entitys);
    List<DepositoPlazoFijoLima> findAll();
    List<DepositoPlazoFijoLima> findByFecha(String fechaInicio, String fechaFin) throws ParseException;
    
}
