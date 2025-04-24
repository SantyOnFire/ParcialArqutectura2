package com.example.Parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parcial.model.EstadisticasJugador;
import com.example.Parcial.repository.EstadisticasJugadorRepository;

@Service
public class EstadisticasJugadorService {

    private final EstadisticasJugadorRepository estadisticasRepository;

    @Autowired
    public EstadisticasJugadorService(EstadisticasJugadorRepository estadisticasRepository) {
        this.estadisticasRepository = estadisticasRepository;
    }

    // Métodos CRUD básicos
    public List<EstadisticasJugador> findAll() {
        return estadisticasRepository.findAll();
    }

    public Optional<EstadisticasJugador> findById(Long id) {
        return estadisticasRepository.findById(id);
    }

    public EstadisticasJugador save(EstadisticasJugador estadisticas) {
        return estadisticasRepository.save(estadisticas);
    }

    public void deleteById(Long id) {
        estadisticasRepository.deleteById(id);
    }

    // Método para consulta nativa 3
    public Integer findTotalGolesByEquipo(Long idEquipo) {
        return estadisticasRepository.findTotalGolesByEquipo(idEquipo);
    }
}