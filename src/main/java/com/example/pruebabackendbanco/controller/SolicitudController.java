package com.example.pruebabackendbanco.controller;

import com.example.pruebabackendbanco.dao.SolicitudDao;
import com.example.pruebabackendbanco.entities.Solicitudes;
import com.example.pruebabackendbanco.services.SoliccitudesServiceImpl;
import com.example.pruebabackendbanco.util.Mensaje;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
@Slf4j
public class SolicitudController {


    @Autowired
    SoliccitudesServiceImpl service;

    @Autowired
    SolicitudDao solicitudDao;

   @GetMapping("/all")
    public ResponseEntity<List<Solicitudes>> listarTodos(){
    List<Solicitudes> lista = service.listaSolicitudes();
     return new ResponseEntity(lista, HttpStatus.OK);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarid(@PathVariable("id")Long id){
       var  solicitud = service.buscarSolicitud(id);
       return new  ResponseEntity(solicitud, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Solicitudes solicitudes) throws Exception  {
        //log.info(String.valueOf(solicitudes));
        //DateTimeFormatter fecha_actual = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
        //String  fecha_ingreso = fecha_actual.format(solicitudes.getFechaIngreso());
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        //LocalDate fecha = LocalDate.parse(fecha_ingreso);


        if(solicitudes.getMonto() < 1000000){
            return  new ResponseEntity(new Mensaje("el monto ni puede ser inferior a 1 millon"), HttpStatus.NOT_ACCEPTABLE);
        }
       // if(LocalDateTime.now().isAfter(ChronoLocalDateTime.from(fecha))){
            //return  new ResponseEntity(new Mensaje("la fecha no puede ser inferior a la actual"), HttpStatus.NOT_ACCEPTABLE);
       // }




        service.guardar(solicitudes);
        return new ResponseEntity(new Mensaje("Se creo con exito la solicitud"), HttpStatus.OK);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Solicitudes solicitudes){
       log.info("ingreso");
        if(!service.existSolicitudId(id)){
            return new ResponseEntity(new Mensaje("no existe solicitud"), HttpStatus.NOT_FOUND);
        }
        if(solicitudes.getMonto() < 1000000){
            return  new ResponseEntity(new Mensaje("el monto ni puede ser inferior a 1 millon"), HttpStatus.NOT_ACCEPTABLE);
        }
        Solicitudes s1 = service.getOne(id).get();
        s1.setEstado(solicitudes.getEstado());
        s1.setMonto(solicitudes.getMonto());
        service.guardar(s1);

        return new ResponseEntity(new Mensaje("Se Actualizo la solicitud"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!service.existSolicitudId(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        service.eliminar(id);
        return new ResponseEntity(new Mensaje("tarea eliminada"), HttpStatus.OK);
    }

}
