package com.example.Parcial.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Parcial.model.Partido;
import com.example.Parcial.service.PartidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/partidos")
@Tag(name = "Partido", description = "API para gestionar partidos de fútbol")
public class PartidoController {

    private final PartidoService partidoService;

    @Autowired
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los partidos", description = "Retorna una lista con todos los partidos registrados")
    public List<Partido> getAllPartidos() {
        return partidoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un partido por ID", description = "Retorna un partido específico según su ID")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Long id) {
        return partidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo partido", description = "Crea un nuevo partido con los datos proporcionados")
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
        Partido savedPartido = partidoService.save(partido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPartido);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un partido", description = "Actualiza los datos de un partido existente según su ID")
    public ResponseEntity<Partido> updatePartido(@PathVariable Long id, @RequestBody Partido partido) {
        return partidoService.findById(id)
                .map(existingPartido -> {
                    partido.setIdPartido(id);
                    return ResponseEntity.ok(partidoService.save(partido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un partido", description = "Elimina un partido existente según su ID")
    public ResponseEntity<Void> deletePartido(@PathVariable Long id) {
        return partidoService.findById(id)
                .map(partido -> {
                    partidoService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/resultados")
    @Operation(summary = "Obtener resultados de partidos", description = "Retorna los resultados de todos los partidos con nombres de equipos")
    public List<Map<String, Object>> getResultadosPartidos() {
        return partidoService.findAllResultados();
    }
}