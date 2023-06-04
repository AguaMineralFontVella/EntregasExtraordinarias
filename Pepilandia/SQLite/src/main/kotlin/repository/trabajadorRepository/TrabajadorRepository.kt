package repository.trabajadorRepository

import models.Trabajador
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que hereda de CrudRepository para poder utilizar sus métodos y que se encarga de gestionar los trabajadores
 * @property findAll Función que devuelve todos los trabajadores
 * @property findByTelefono Función que devuelve un trabajador según su teléfono
 * @property findByEmail Función que devuelve un trabajador según su email
 * @property findByNombreUsuario Función que devuelve un trabajador según su nombre de usuario
 * @property findByEspecialidad Función que devuelve todos los trabajadores según su especialidad
 * @property findByIsResponsable Función que devuelve todos los trabajadores según si es responsable o no
 */
interface TrabajadorRepository : CrudRepository<Trabajador, Long> {
    fun findAll(): List<Trabajador>
    fun findByTelefono(telefono: String): Trabajador?
    fun findByEmail(email: String): Trabajador?
    fun findByNombreUsuario(nombreUsuario: String): Trabajador?
    fun findByEspecialidad(especialidad: String): List<Trabajador>
    fun findByIsResponsable(isResponsable: Boolean): List<Trabajador>
}