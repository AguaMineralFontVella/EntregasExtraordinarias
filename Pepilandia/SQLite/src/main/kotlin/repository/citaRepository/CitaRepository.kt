package repository.citaRepository

import models.Cita
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que hereda de CrudRepository para poder utilizar sus métodos y que se encarga de gestionar las citas
 * @property findAll Función que devuelve todas las citas
 */
interface CitaRepository : CrudRepository<Cita, Long> {
        fun findAll(): List<Cita>
}