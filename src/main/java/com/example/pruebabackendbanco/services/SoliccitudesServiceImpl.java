package com.example.pruebabackendbanco.services;

import com.example.pruebabackendbanco.dao.SolicitudDao;
import com.example.pruebabackendbanco.entities.Solicitudes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SoliccitudesServiceImpl  implements SolicitudeServices{

    @Autowired
    private SolicitudDao solicitudDao;


    @Override
    @Transactional
    public List<Solicitudes> listaSolicitudes() {
        return  (List<Solicitudes>) solicitudDao.findAll();
    }

    @Override
    public void guardar(Solicitudes solicitudes) {
        solicitudDao.save(solicitudes);
    }

    @Override
    public void eliminar(Long id) {
        solicitudDao.deleteById(id);
    }

    @Override
    public Solicitudes buscarSolicitud(Long id) {
        return  solicitudDao.findById(id).orElse(null);
    }



    @Override
    public Boolean existSolicitudId(Long id) {
        return  solicitudDao.existsById(id);
    }

    @Override
    public Optional<Solicitudes> getOne(Long id) {
        return solicitudDao.findById(id);
    }




}
