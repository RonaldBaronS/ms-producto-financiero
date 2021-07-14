package com.unmsm.producto.service.impl;

import com.unmsm.producto.dao.ProductoFinancieroDao;
import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.RegistrarBaseRequest;
import com.unmsm.producto.service.ProductoFinancieroService;
import com.unmsm.producto.util.ProductoFinancieroConstante;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
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
            entity.setFecha(new Date());
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
             entity.setFecha(new Date());
             listDepositos.add(entity);
             dao.procesar(listDepositos);
         }
    }
    
    @Override
    public List<DepositoPlazoFijoLima> listarDepositoPlazoFijoLima() {
        return dao.findAll();
    }
    
    private String obtenerFechaDelSistema() {
        Calendar localTime = Calendar.getInstance();
        localTime = new GregorianCalendar(TimeZone.getTimeZone("America/Lima"));
        localTime.setTimeInMillis(localTime.getTimeInMillis());
        Date fecha1 = new Date(localTime.getTimeInMillis());
        SimpleDateFormat isoFormat = new SimpleDateFormat(ProductoFinancieroConstante.STRING_DATE_TIME_FORMATTER);
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC-5"));
        String startDateTime = isoFormat.format(fecha1);
        return startDateTime;
    }

    @Override
    public List<DepositoPlazoFijoLima> filtrarFecha(Date fechaInicio,Date fechaFin) {
        log.info("ProductoFinancieroServiceImpl.filtrarFecha");
        log.info("FechaInicio :" + fechaInicio);
        log.info("FechaFin :" + fechaFin);
        List<DepositoPlazoFijoLima> listar = dao.findByFecha(fechaInicio, fechaFin);
        if(listar!=null) {
            log.info("entrando al dao >>>>  ");
            return dao.findByFecha(fechaInicio, fechaFin);
        }
        return null;
    }

    /*@Override
    public List<DepositoPlazoFijoLima> filtrarFecha2(Date fechaInicio, Date fechaFin) {
        
        return dao.findByFechaInicioAndFechaFin(fechaInicio, fechaFin);
    }*/



}