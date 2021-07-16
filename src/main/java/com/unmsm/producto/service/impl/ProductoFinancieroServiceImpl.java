package com.unmsm.producto.service.impl;

import com.unmsm.producto.dao.ProductoFinancieroDao;
import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.RegistrarBaseRequest;
import com.unmsm.producto.service.ProductoFinancieroService;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductoFinancieroServiceImpl implements ProductoFinancieroService {
    
    @Autowired
    private ProductoFinancieroDao dao;
    
    @Override
    public void registrarDepositoPlazoFijoLima(RegistrarBaseRequest request) {
        log.info("ProductoFinancieroServiceImpl.registrarDepositoPlazoFijoLima");
        String cadenaCodificada = request.getContent();
        byte[] bytesDecodificados = Base64.getDecoder().decode(cadenaCodificada);
        String cadenaDecodificada = new String(bytesDecodificados);
        String separadorColumna = ";";
        String separadorFila = "\n";
        String[] data = cadenaDecodificada.split(separadorFila);
        for (String row : data) {
            String[] columnData = row.split(separadorColumna);
            DepositoPlazoFijoLima entity = new DepositoPlazoFijoLima();
            entity.setEntidad(columnData[0]);
            entity.setTasa(columnData[1].trim());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            LocalDate fecha = LocalDate.parse(obtenerFechaDelSistema(), formato); 
            entity.setFecha(fecha);
            log.info("ProductoFinancieroServiceImpl.registrarDepositoPlazoFijoLima.DepositoPlazoFijoLima.getFecha:"
                    + entity.getFecha());
            dao.save(entity);
        }
    }

    @Override
    public void procesarDepositoPlazoFijoLima(RegistrarBaseRequest request) {
        log.info("ProductoFinancieroServiceImpl.procesarDepositoPlazoFijoLima");
        List<DepositoPlazoFijoLima> listDepositos = new ArrayList<>();
         String cadenaCodificada = request.getContent();
         byte[] bytesDecodificados = Base64.getDecoder().decode(cadenaCodificada);
         String cadenaDecodificada = new String(bytesDecodificados);
         String separadorColumna = ";";
         String separadorFila = "\n";
         String[] data = cadenaDecodificada.split(separadorFila);
         for (String row : data) {
             String[] columnData = row.split(separadorColumna);
             DepositoPlazoFijoLima entity = new DepositoPlazoFijoLima();
             entity.setEntidad(columnData[0]);
             entity.setTasa(columnData[1].trim());
             DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate fecha = LocalDate.parse(obtenerFechaDelSistema(), formato); 
             entity.setFecha(fecha);
             listDepositos.add(entity);
             dao.procesar(listDepositos);
         }
    }
    
    @Override
    public List<DepositoPlazoFijoLima> listarDepositoPlazoFijoLima() {
        return dao.findAll();
    }
    
    @Override
    public List<DepositoPlazoFijoLima> filtrarFecha(String fechaInicio, String fechaFin) throws ParseException {
        log.info("ProductoFinancieroServiceImpl.filtrarFecha");
        log.info("FechaInicio :" + fechaInicio);
        log.info("FechaFin :" + fechaFin);
        return dao.findByFecha(fechaInicio, fechaFin);
    }
    
    private String obtenerFechaDelSistema() {
        Calendar localTime = Calendar.getInstance();
        localTime.setTimeInMillis(localTime.getTimeInMillis());
        Date fecha1 = new Date(localTime.getTimeInMillis());
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateTime = isoFormat.format(fecha1);
        return startDateTime;
    }
    
}