package com.example.Parcial.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Parcial.model.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // 1. Partidos por equipo local
    @Query(value = "SELECT * FROM partido WHERE equipo_local = :equipoId", nativeQuery = true)
    List<Partido> findByEquipoLocal(@Param("equipoId") Long equipoId);

    // 2. Partidos por equipo visitante
    @Query(value = "SELECT * FROM partido WHERE equipo_visita = :equipoId", nativeQuery = true)
    List<Partido> findByEquipoVisitante(@Param("equipoId") Long equipoId);

    // 3. Partidos entre fechas
    @Query(value = "SELECT * FROM partido WHERE fecha BETWEEN :inicio AND :fin", nativeQuery = true)
    List<Partido> findByFechaBetween(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    // 4. Partidos con más de X goles totales
    @Query(value = "SELECT * FROM partido WHERE (goles_local + goles_visita) > :totalGoles", nativeQuery = true)
    List<Partido> findByTotalGolesGreaterThan(@Param("totalGoles") int totalGoles);
}