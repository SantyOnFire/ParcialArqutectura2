package com.example.Parcial.repository;

import com.example.Parcial.model.EstadisticasJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadisticasJugadorRepository extends JpaRepository<EstadisticasJugador, Long> {

    // 1. Estadísticas por jugador
    @Query(value = "SELECT * FROM estadisticas_jugador WHERE id_jugador = :jugadorId", nativeQuery = true)
    List<EstadisticasJugador> findByJugadorId(@Param("jugadorId") Long jugadorId);

    // 2. Estadísticas por partido
    @Query(value = "SELECT * FROM estadisticas_jugador WHERE id_partido = :partidoId", nativeQuery = true)
    List<EstadisticasJugador> findByPartidoId(@Param("partidoId") Long partidoId);

    // 3. Jugadores con tarjetas rojas
    @Query(value = "SELECT DISTINCT id_jugador FROM estadisticas_jugador WHERE tarjetas_rojas > 0", nativeQuery = true)
    List<Long> findJugadoresConTarjetasRojas();

    // 4. Top asistentes
    @Query(value = "SELECT id_jugador, SUM(asistencias) as total_asistencias FROM estadisticas_jugador GROUP BY id_jugador ORDER BY total_asistencias DESC LIMIT :limit", nativeQuery = true)
    List<Object[]> findTopAsistentes(@Param("limit") int limit);

    Integer findTotalGolesByEquipo(Long idEquipo);
}
