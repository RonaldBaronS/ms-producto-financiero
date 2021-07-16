package com.unmsm.producto.dao.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<DepositoPlazoFijoLima> findByFecha(String fechaInicio, String fechaFin) throws ParseException {
        log.info("ProductoFinancieroDaoImpl.findByFecha");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha1 = LocalDate.parse(fechaInicio, formato); 
        LocalDate fecha2 = LocalDate.parse(fechaFin, formato);
        log.info("fecha 1 : "+fecha1);
        log.info("fecha 2:"+fecha2);
        return repository.findByFechaBetween(fecha1, fecha2);
    }
    
}