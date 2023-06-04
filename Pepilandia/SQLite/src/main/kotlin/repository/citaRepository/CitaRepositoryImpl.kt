package repository.citaRepository

import db.DatabaseManager
import models.Cita
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que implementa la interfaz CitaRepository y que se encarga de gestionar las citas
 * @property findAll Función que devuelve todas las citas
 * @property findById Función que devuelve una cita según su id
 * @property save Función que guarda una cita
 * @property update Función que actualiza una cita
 * @property delete Función que elimina una cita
 */
class CitaRepositoryImpl : CitaRepository{

    /**
     * Función que devuelve todas las citas
     * @return Lista de citas
     */
    override fun findAll(): List<Cita> {
        DatabaseManager.open()
        val citas = mutableListOf<Cita>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM cita"
            val result = db.select(sql)
            result?.let{
                while(result.next()){
                    val cita = Cita(
                        id = result.getInt("id"),
                        idTrabajador = result.getInt("id_trabajador"),
                        idVehiculo = result.getInt("id_vehiculo"),
                        fecha = LocalDate.parse(result.getString("fecha")),
                        hora = result.getString("hora"),
                    )
                    citas.add(cita)
                }
            }
        }
        return citas
    }

    /**
     * Función que devuelve una cita según su id
     * @param id Id de la cita
     * @return Cita
     */
    override fun findById(id: Long): Cita? {
        DatabaseManager.open()
        var cita: Cita? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM cita WHERE id = ?"
            val result = db.select(sql, id)
            result?.let{
                while(result.next()){
                    cita = Cita(
                        id = result.getInt("id"),
                        idTrabajador = result.getInt("id_trabajador"),
                        idVehiculo = result.getInt("id_vehiculo"),
                        fecha = LocalDate.parse(result.getString("fecha")),
                        hora = result.getString("hora"),
                        )
                }
            }
        }
        return cita
    }

    /**
     * Función que guarda una cita
     * @param entity Cita
     * @return Cita
     */
    override fun save(entity: Cita): Cita {
        DatabaseManager.open()
        var cita: Cita? = null
        DatabaseManager.use{ db ->
            val sql = "INSERT INTO cita(id_trabajador, id_vehiculo, fecha, hora) VALUES (?, ?, ?, ?)"
            val result = db.update(sql, entity.idTrabajador, entity.idVehiculo, entity.fecha, entity.hora)
            if (result != null) {
                cita = entity
            }
        }
        return cita!!
    }

    /**
     * Función que actualiza una cita
     * @param entity Cita
     * @return Cita
     */
    override fun update(entity: Cita): Cita {
        DatabaseManager.open()
        var cita: Cita? = null
        DatabaseManager.use{ db ->
            val sql = "UPDATE cita SET id_trabajador = ?, id_vehiculo = ?, fecha = ?, hora = ? WHERE id = ?"
            val result = db.update(sql, entity.idTrabajador, entity.idVehiculo, entity.fecha, entity.hora, entity.id)
            if (result != null) {
                cita = entity
            }
        }
        return cita!!
    }

    /**
     * Función que elimina una cita
     * @param entity Cita
     * @return Boolean
     */
    override fun delete(entity: Cita): Boolean {
        DatabaseManager.open()
        var deleted = false
        DatabaseManager.use{ db ->
            val sql = "DELETE FROM cita WHERE id = ?"
            val result = db.update(sql, entity.id)
            if (result == 1) {
                deleted = true
            }
        }
        return deleted
    }
}