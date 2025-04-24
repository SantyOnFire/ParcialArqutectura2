package com.example.Parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parcial.model.Entrenador;
import com.example.Parcial.repository.EntrenadorRepository;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    @Autowired
    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    public Optional<Entrenador> findById(Long id) {
        return entrenadorRepository.findById(id);
    }

    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public void deleteById(Long id) {
        entrenadorRepository.deleteById(id);
    }

    public List<Entrenador> findByEquipoId(Long equipoId) {
        return entrenadorRepository.findByEquipoId(equipoId);
    }
}
