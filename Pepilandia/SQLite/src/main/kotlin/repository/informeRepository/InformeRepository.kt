package repository.informeRepository

import models.Informe
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que hereda de CrudRepository para poder utilizar sus métodos y que se encarga de gestionar los informes
 * @property findAll Función que devuelve todos los informes
 * @property findByIsFavorable Función que devuelve todos los informes favorables
 */
interface InformeRepository : CrudRepository<Informe, Long>{
    fun findAll(): List<Informe>
    fun findByIsFavorable(): List<Informe>
}