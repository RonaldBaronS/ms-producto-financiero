package com.unmsm.producto.dao.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.unmsm.producto.dao.ProductoFinancieroDao;
import com.unmsm.producto.dao.ProductoFinancieroDaoRepository;
import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductoFinancieroDaoImpl implements ProductoFinancieroDao {
    
    @Autowired
    private ProductoFinancieroDaoRepository repository;
    
    @Override
    public void save(DepositoPlazoFijoLima entity) {
        repository.save(entity);
    }
    
    @Override
    public void procesar(List<DepositoPlazoFijoLima> entities) {
        if(existeRegistro()) {
            repository.deleteAll();
            repository.saveAll(entities);
        }else {
            repository.saveAll(entities);
        }
    }
    
    private boolean existeRegistro() {
        if(repository.count()==0) {
            return false;
        }else {
            return true;
        }
    }
    
    @Override
    public List<DepositoPlazoFijoLima> findAll() {
        return (List<DepositoPlazoFijoLima>) repository.findAll();
    }

    @Override
    public List<DepositoPlazoFijoLima> findByFecha(Date fechaInicio, Date fechaFin) {
        log.info("ProductoFinancieroDaoImpl.findByFecha");
        return repository.findByFecha(fechaInicio, fechaFin);
    }

    /*@Override
    public List<DepositoPlazoFijoLima> findByFechaInicioAndFechaFin(Date fechaInicio, Date fechaFin) {
        return repository.findByFechaInicioAndFechaFin(fechaInicio, fechaFin);
    }*/
    
    
}