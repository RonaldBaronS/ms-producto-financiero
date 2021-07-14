package com.unmsm.producto.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;

public interface ProductoFinancieroDaoRepository extends CrudRepository<DepositoPlazoFijoLima, Integer> {

    /*@Query("SELECT id,entidad,fecha,tasa FROM DepositoPlazoFijoLima WHERE fecha=:fecha")
    public List<DepositoPlazoFijoLima> findByFecha(@Param("fecha") String fecha);*/
 
    @Query("SELECT id,entidad,tasa,fecha FROM DepositoPlazoFijoLima WHERE fecha BETWEEN ?1 AND ?2")
    public List<DepositoPlazoFijoLima> findByFecha(Date fechaInicio,Date fechaFin);
    
    /*@Query("SELECT id,entidad,tasa,fecha FROM DepositoPlazoFijoLima WHERE fecha BETWEEN ?1 AND ?2")
    public List<DepositoPlazoFijoLima> findByFechaInicioAndFechaFin(@Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin);*/

}
