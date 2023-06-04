package repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz que se encarga de gestionar las operaciones CRUD
 * @property findById Función que devuelve un objeto según su id
 * @property save Función que guarda un objeto
 * @property update Función que actualiza un objeto
 * @property delete Función que elimina un objeto
 */
interface CrudRepository<T, ID> {
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun update(entity: T): T
    fun delete(entity: T): Boolean
}