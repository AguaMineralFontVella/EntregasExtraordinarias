package repository.vehiculoRepository

import models.Vehiculo
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que hereda de CrudRepository para poder utilizar sus métodos y que se encarga de gestionar los vehiculos
 * @property findAll Función que devuelve todos los vehiculos
 * @property findByModelo Función que devuelve un vehiculo según su modelo
 * @property findByMatricula Función que devuelve un vehiculo según su matricula
 * @property findByDniPropietario Función que devuelve todos los vehiculos según el dni de su propietario
 */
interface VehiculoRepository : CrudRepository<Vehiculo, Long> {
    fun findAll(): List<Vehiculo>
    fun findByModelo(modelo: String): Vehiculo?
    fun findByMatricula(matricula: String): Vehiculo?
    fun findByDniPropietario(dni: String): List<Vehiculo>
}