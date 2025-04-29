package com.example.Parcial.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Parcial.model.Partido;
import com.example.Parcial.repository.PartidoRepository;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    @Autowired
    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    public Optional<Partido> findById(Long id) {
        return partidoRepository.findById(id);
    }

    public Partido save(Partido partido) {
        return partidoRepository.save(partido);
    }

    public void deleteById(Long id) {
        partidoRepository.deleteById(id);
    }

    // Método para consulta nativa 4
    public List<Map<String, Object>> findAllPartidosConEquipos() {
        return partidoRepository.findAllPartidosConEquipos();
    }

    // Método para resolver el error en PartidoController
    public List<Map<String, Object>> findAllResultados() {
        return findAllPartidosConEquipos();
    }
}