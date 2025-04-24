package com.example.Parcial.controller;

import java.util.List;

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

import com.example.Parcial.model.Equipo;
import com.example.Parcial.service.EquipoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipo", description = "API para gestionar equipos de fútbol")
public class EquipoController {

    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los equipos", description = "Retorna una lista con todos los equipos registrados")
    public List<Equipo> getAllEquipos() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID", description = "Retorna un equipo específico según su ID")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo con los datos proporcionados")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.save(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo", description = "Actualiza los datos de un equipo existente según su ID")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        return equipoService.findById(id)
                .map(existingEquipo -> {
                    equipo.setIdEquipo(id);
                    return ResponseEntity.ok(equipoService.save(equipo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo", description = "Elimina un equipo existente según su ID")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        return equipoService.findById(id)
                .map(equipo -> {
                    equipoService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}