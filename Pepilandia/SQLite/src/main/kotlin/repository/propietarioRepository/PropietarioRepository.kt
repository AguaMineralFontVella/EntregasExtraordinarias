package repository.propietarioRepository

import models.Propietario
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que hereda de CrudRepository para poder utilizar sus métodos y que se encarga de gestionar los propietarios
 * @property findAll Función que devuelve todos los propietarios
 * @property findByDni Función que devuelve un propietario según su dni
 * @property findByTelefono Función que devuelve un propietario según su teléfono
 */
interface PropietarioRepository : CrudRepository<Propietario, Long> {
    fun findAll(): List<Propietario>
    fun findByDni(dni: String): Propietario?
    fun findByTelefono(telefono: String): Propietario?
}