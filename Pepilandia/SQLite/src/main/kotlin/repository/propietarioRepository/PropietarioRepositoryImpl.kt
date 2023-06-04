package repository.propietarioRepository

import db.DatabaseManager
import models.Propietario

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que implementa la interfaz PropietarioRepository y que se encarga de gestionar los propietarios
 * @property findByDni Función que devuelve un propietario según su dni
 * @property findByTelefono Función que devuelve un propietario según su teléfono
 * @property findAll Función que devuelve todos los propietarios
 * @property findById Función que devuelve un propietario según su id
 * @property save Función que guarda un propietario
 * @property update Función que actualiza un propietario
 * @property delete Función que elimina un propietario
 */
class PropietarioRepositoryImpl : PropietarioRepository {

    /**
     * Función que devuelve un propietario según su dni
     * @param dni Dni del propietario
     * @return Propietario
     */
    override fun findByDni(dni: String): Propietario? {
        DatabaseManager.open()
        var propietario: Propietario? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM propietario WHERE dni = ?"
            val result = db.select(sql, dni)
            result?.let{
                while(result.next()){
                    propietario = Propietario(
                        id = result.getInt("id"),
                        dni = result.getString("dni"),
                        nombre = result.getString("nombre"),
                        apellidos = result.getString("apellidos"),
                        telefono = result.getString("telefono")
                    )
                }
            }
        }
        return propietario
    }

    /**
     * Función que devuelve un propietario según su teléfono
     * @param telefono Teléfono del propietario
     * @return Propietario
     */
    override fun findByTelefono(telefono: String): Propietario? {
        DatabaseManager.open()
        var propietario: Propietario? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM propietario WHERE telefono = ?"
            val result = db.select(sql, telefono)
            result?.let{
                while(result.next()){
                    propietario = Propietario(
                        id = result.getInt("id"),
                        dni = result.getString("dni"),
                        nombre = result.getString("nombre"),
                        apellidos = result.getString("apellidos"),
                        telefono = result.getString("telefono")
                    )
                }
            }
        }
        return propietario
    }

    /**
     * Función que devuelve todos los propietarios
     * @return Lista de propietarios
     */
    override fun findAll(): List<Propietario> {
        DatabaseManager.open()
        val propietarios = mutableListOf<Propietario>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM propietario"
            val result = db.select(sql)
            result?.let{
                while(result.next()){
                    val propietario = Propietario(
                        id = result.getInt("id"),
                        dni = result.getString("dni"),
                        nombre = result.getString("nombre"),
                        apellidos = result.getString("apellidos"),
                        telefono = result.getString("telefono")
                    )
                    propietarios.add(propietario)
                }
            }
        }
        return propietarios
    }

    /**
     * Función que devuelve un propietario según su id
     * @param id Id del propietario
     * @return Propietario
     */
    override fun findById(id: Long): Propietario? {
        DatabaseManager.open()
        var propietario: Propietario? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM propietario WHERE id = ?"
            val result = db.select(sql, id)
            result?.let{
                while(result.next()){
                    propietario = Propietario(
                        id = result.getInt("id"),
                        dni = result.getString("dni"),
                        nombre = result.getString("nombre"),
                        apellidos = result.getString("apellidos"),
                        telefono = result.getString("telefono")
                    )
                }
            }
        }
        return propietario
    }

    /**
     * Función que guarda un propietario
     * @param entity Propietario a guardar
     * @return Propietario
     */
    override fun save(entity: Propietario): Propietario {
        DatabaseManager.open()
        var propietario : Propietario? = null
        DatabaseManager.use{ db ->
            val sql = "INSERT INTO propietario(dni, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)"
            val result = db.update(sql, entity.dni, entity.nombre, entity.apellidos, entity.telefono)
            if (result != null) {
                propietario = entity
            }
        }
        return propietario!!
    }

    /**
     * Función que actualiza un propietario
     * @param entity Propietario a actualizar
     * @return Propietario
     */
    override fun update(entity: Propietario): Propietario {
        DatabaseManager.open()
        var propietario : Propietario? = null
        DatabaseManager.use{ db ->
            val sql = "UPDATE propietario SET dni = ?, nombre = ?, apellidos = ?, telefono = ? WHERE id = ?"
            val result = db.update(sql, entity.dni, entity.nombre, entity.apellidos, entity.telefono, entity.id)
            if (result != null) {
                propietario = entity
            }
        }
        return propietario!!
    }

    /**
     * Función que elimina un propietario
     * @param entity Propietario a eliminar
     * @return Boolean
     */
    override fun delete(entity: Propietario): Boolean {
        DatabaseManager.open()
        var deleted = false
        DatabaseManager.use{ db ->
            val sql = "DELETE FROM propietario WHERE id = ?"
            val result = db.update(sql, entity.id)
            if (result == 1) {
                deleted = true
            }
        }
        return deleted
    }
}