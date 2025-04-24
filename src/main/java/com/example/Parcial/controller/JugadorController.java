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

import com.example.Parcial.model.Jugador;
import com.example.Parcial.service.JugadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugador", description = "API para gestionar jugadores de fútbol")
public class JugadorController {

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los jugadores", description = "Retorna una lista con todos los jugadores registrados")
    public List<Jugador> getAllJugadores() {
        return jugadorService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un jugador por ID", description = "Retorna un jugador específico según su ID")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable Long id) {
        return jugadorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo jugador", description = "Crea un nuevo jugador con los datos proporcionados")
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        Jugador savedJugador = jugadorService.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJugador);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un jugador", description = "Actualiza los datos de un jugador existente según su ID")
    public ResponseEntity<Jugador> updateJugador(@PathVariable Long id, @RequestBody Jugador jugador) {
        return jugadorService.findById(id)
                .map(existingJugador -> {
                    jugador.setId(id);
                    return ResponseEntity.ok(jugadorService.save(jugador));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un jugador", description = "Elimina un jugador existente según su ID")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
        return jugadorService.findById(id)
                .map(jugador -> {
                    jugadorService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}