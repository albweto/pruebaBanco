package com.example.pruebabackendbanco.dao;

import com.example.pruebabackendbanco.entities.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SolicitudDao  extends JpaRepository<Solicitudes, Long> {


}
