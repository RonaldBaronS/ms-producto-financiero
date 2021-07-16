package com.unmsm.producto.controller;

import com.unmsm.producto.dao.entity.DepositoPlazoFijoLima;
import com.unmsm.producto.model.MessageResponse;
import com.unmsm.producto.model.RegistrarBaseRequest;
import com.unmsm.producto.service.ProductoFinancieroService;
import com.unmsm.producto.util.ProductoFinancieroConstante;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping("/deposito/plazoFijo/lima/rango")
    public List<DepositoPlazoFijoLima> filtroFechas(
            @RequestParam(name = "fechaInicio") String fechaInicio,
            @RequestParam(name = "fechaFin") String fechaFin) throws ParseException {
        return service.filtrarFecha(fechaInicio, fechaFin);
    }
    
}
