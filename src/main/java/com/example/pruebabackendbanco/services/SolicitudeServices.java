package com.example.pruebabackendbanco.services;

import com.example.pruebabackendbanco.entities.Solicitudes;

import java.util.List;
import java.util.Optional;

public interface SolicitudeServices {

    public List<Solicitudes> listaSolicitudes();
    public void guardar(Solicitudes solicitudes);
    public void  eliminar(Long id);
    public Solicitudes buscarSolicitud(Long id);
    public Boolean existSolicitudId(Long id);
    public Optional<Solicitudes> getOne(Long id);

}
