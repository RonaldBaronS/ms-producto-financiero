package com.unmsm.producto.service;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.RegistrarBaseRequest;
import java.util.Date;
import java.util.List;

public interface ProductoFinancieroService {
    
    void registrarDepositoPlazoFijoLima(RegistrarBaseRequest request);
    void procesarDepositoPlazoFijoLima(RegistrarBaseRequest request);
    List<DepositoPlazoFijoLima> listarDepositoPlazoFijoLima();
    List<DepositoPlazoFijoLima> filtrarFecha(Date fechaInicio,Date fechaFin);
    //List<DepositoPlazoFijoLima> filtrarFecha2(Date fechaInicio,Date fechaFin);
}
