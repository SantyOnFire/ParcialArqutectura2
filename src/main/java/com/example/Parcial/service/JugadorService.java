package com.example.Parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parcial.model.Jugador;
import com.example.Parcial.repository.JugadorRepository;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    @Autowired
    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // Métodos CRUD básicos
    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> findById(Long id) {
        return jugadorRepository.findById(id);
    }

    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void deleteById(Long id) {
        jugadorRepository.deleteById(id);
    }

    // Métodos para consultas nativas
    public List<Jugador> findJugadoresByEquipo(Long idEquipo) {
        return jugadorRepository.findJugadoresByEquipo(idEquipo);
    }

    public List<Jugador> findJugadoresConMasGoles(Integer cantidadGoles) {
        return jugadorRepository.findJugadoresConMasGoles(cantidadGoles);
    }
}