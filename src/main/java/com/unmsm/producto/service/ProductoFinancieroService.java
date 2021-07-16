package com.unmsm.producto.service;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.RegistrarBaseRequest;
import java.text.ParseException;
import java.util.List;

public interface ProductoFinancieroService {
    
    void registrarDepositoPlazoFijoLima(RegistrarBaseRequest request);
    void procesarDepositoPlazoFijoLima(RegistrarBaseRequest request);
    List<DepositoPlazoFijoLima> listarDepositoPlazoFijoLima();
    List<DepositoPlazoFijoLima> filtrarFecha(String fechaInicio,String fechaFin)throws ParseException;
    
}
