package com.unmsm.producto.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;

public interface ProductoFinancieroDaoRepository extends CrudRepository<DepositoPlazoFijoLima, Integer> {
    
    @Query("SELECT dep FROM DepositoPlazoFijoLima dep WHERE dep.fecha BETWEEN ?1 AND ?2")
    public List<DepositoPlazoFijoLima> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
}
