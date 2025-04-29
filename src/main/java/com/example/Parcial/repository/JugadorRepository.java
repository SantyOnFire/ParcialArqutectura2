package com.example.Parcial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Parcial.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // 1. Jugadores por equipo (ya existe)
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :equipoId", nativeQuery = true)
    List<Jugador> findByEquipoId(@Param("equipoId") Long equipoId);

    // 2. Jugadores con más de X goles (ya existe)
    @Query(value = "SELECT j.* FROM jugador j JOIN estadisticas_jugador e ON j.id_jugador = e.id_jugador GROUP BY j.id_jugador HAVING SUM(e.goles) > :minGoles", nativeQuery = true)
    List<Jugador> findByGolesGreaterThan(@Param("minGoles") int minGoles);

    // 3. Jugadores por posición
    @Query(value = "SELECT * FROM jugador WHERE posicion = :posicion", nativeQuery = true)
    List<Jugador> findByPosicion(@Param("posicion") String posicion);

    // 4. Jugadores por nacionalidad
    @Query(value = "SELECT * FROM jugador WHERE nacionalidad = :nacionalidad", nativeQuery = true)
    List<Jugador> findByNacionalidad(@Param("nacionalidad") String nacionalidad);

    List<Jugador> findJugadoresConMasGoles(Integer cantidadGoles);

    List<Jugador> findJugadoresByEquipo(Long idEquipo);
}
