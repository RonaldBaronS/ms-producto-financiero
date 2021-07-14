package com.unmsm.producto.controller;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.MessageResponse;
import com.unmsm.producto.model.RegistrarBaseRequest;
import com.unmsm.producto.service.ProductoFinancieroService;
import com.unmsm.producto.util.ProductoFinancieroConstante;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/productofinanciero")
public class ProductoFinancieroController {
    
    @Autowired
    private ProductoFinancieroService service;
    
    @PostMapping("/deposito/plazoFijo/porLima")
    public ResponseEntity<MessageResponse> registrar(@RequestBody RegistrarBaseRequest request) {
        service.registrarDepositoPlazoFijoLima(request);
        return new ResponseEntity<>(new MessageResponse(ProductoFinancieroConstante.MENSAJE_EXITOSO),
                HttpStatus.CREATED);
    }
    
    @PostMapping("/deposito/plazoFijo/lima")
    public ResponseEntity<MessageResponse> procesar(@RequestBody RegistrarBaseRequest request) {
        service.procesarDepositoPlazoFijoLima(request);
        return new ResponseEntity<>(new MessageResponse(ProductoFinancieroConstante.MENSAJE_EXITOSO),
                HttpStatus.CREATED);
    }
    
    @GetMapping("/deposito/plazoFijo/lima")
    public List<DepositoPlazoFijoLima> listar(){
        return service.listarDepositoPlazoFijoLima();
    }
    
    /*@GetMapping("/deposito/plazoFijo/lima/rango")
    public List<DepositoPlazoFijoLima> filtroFechas(
            @RequestParam(name="fechaInicio",defaultValue = "#{new java.util.Date()") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) java.util.Date start,
            @RequestParam(name="fechaFin",defaultValue = "#{new java.util.Date()") @DateTimeFormat(pattern="yyyy-MM-dd") java.util.Date end
            ){
        return service.filtrarFecha(start, end);
    }*/
    /*@GetMapping("/deposito/plazoFijo/lima/rango")
    public List<DepositoPlazoFijoLima> filtroFechas(
            @RequestParam(name="fechaInicio",defaultValue = "#{new Date()}") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam(name="fechaFin",defaultValue = "#{new Date()}") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date fechaFin
            ){
        log.info("ProductoFinancieroController.filtroFechas");
        log.info("FechaInicio :" + fechaInicio);
        log.info("FechaFin :" + fechaFin);
        
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); 
        
        
        return service.filtrarFecha(fechaInicio, fechaFin);
    }*/
    
    @GetMapping("/deposito/plazoFijo/lima/rango")
    public List<DepositoPlazoFijoLima> filtroFechas(
            @RequestParam(name="fechaInicio") String fechaInicio,
            @RequestParam(name="fechaFin") String fechaFin
            ) throws ParseException {
            java.util.Date utilDate = new java.util.Date();
            System.out.println("java.util.Date time    : " + utilDate);
            java.sql.Timestamp sqlTS = new java.sql.Timestamp(utilDate.getTime());
            System.out.println("java.sql.Timestamp time: " + sqlTS);
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
            fechaInicio = df.format(utilDate);
            fechaFin = df.format(utilDate);
            
            System.out.println(fechaInicio);
            System.out.println(fechaFin);
            Date fechaInicioNuevo = df.parse("fechaInicio");
            Date fechaFinNuevo = df.parse("fechaFin");
            
        
        return service.filtrarFecha(fechaInicioNuevo, fechaFinNuevo);
    }
    
    
    
    
    
    
    
    
    
    
    /*@GetMapping("/deposito/plazoFijo/lima/rango2")
    public List<DepositoPlazoFijoLima> filtroFechas2(
            @RequestParam(name="fechaInicio") Date fechaInicio,
            @RequestParam(name="fechaFin")Date fechaFin
            ) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaInicioNuevo = formato.parse("fechaInicio");
 
        return service.filtrarFecha2(fechaInicioNuevo, fechaFin);
    }*/
}
