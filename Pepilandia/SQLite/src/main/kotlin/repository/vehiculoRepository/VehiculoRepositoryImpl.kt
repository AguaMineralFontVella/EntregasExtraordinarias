package repository.vehiculoRepository

import db.DatabaseManager
import models.Vehiculo
import java.text.SimpleDateFormat
import java.time.ZoneId

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que implementa la interfaz VehiculoRepository y que se encarga de gestionar los vehiculos
 * @property findByModelo Función que devuelve un vehiculo según su modelo
 * @property findByMatricula Función que devuelve un vehiculo según su matricula
 * @property findByDniPropietario Función que devuelve todos los vehiculos según el dni de su propietario
 * @property findAll Función que devuelve todos los vehiculos
 * @property findById Función que devuelve un vehiculo según su id
 * @property save Función que guarda un vehiculo
 * @property update Función que actualiza un vehiculo
 * @property delete Función que elimina un vehiculo
 */
class VehiculoRepositoryImpl : VehiculoRepository {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    /**
     * Función que devuelve un vehiculo según su modelo
     * @param modelo Modelo del vehiculo
     * @return Vehiculo
     */
    override fun findByModelo(modelo: String): Vehiculo? {
        DatabaseManager.open()
        var vehiculo: Vehiculo? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM vehiculo WHERE modelo = ?"
            val result = db.select(sql, modelo)
            result?.let{
                while(result.next()){
                    vehiculo = Vehiculo(
                        id = result.getInt("id"),
                        marca = result.getString("marca"),
                        modelo = result.getString("modelo"),
                        matricula = result.getString("matricula"),
                        fechaMatriculacion = dateFormat.parse(result.getString("fecha_matriculacion")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        fechaUltimaRevision = dateFormat.parse(result.getString("fecha_ultima_revision")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        dniPropietario = result.getString("dni_propietario"),
                    )
                }
            }
        }
        return vehiculo
    }

    /**
     * Función que devuelve un vehiculo según su matricula
     * @param matricula Matricula del vehiculo
     * @return Vehiculo
     */
    override fun findByMatricula(matricula: String): Vehiculo? {
        DatabaseManager.open()
        var vehiculo: Vehiculo? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM vehiculo WHERE matricula = ?"
            val result = db.select(sql, matricula)
            result?.let{
                while(result.next()){
                    vehiculo = Vehiculo(
                        id = result.getInt("id"),
                        marca = result.getString("marca"),
                        modelo = result.getString("modelo"),
                        matricula = result.getString("matricula"),
                        fechaMatriculacion = dateFormat.parse(result.getString("fecha_matriculacion")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        fechaUltimaRevision = dateFormat.parse(result.getString("fecha_ultima_revision")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        dniPropietario = result.getString("dni_propietario"),
                    )
                }
            }
        }
        return vehiculo
    }

    /**
     * Función que devuelve todos los vehiculos según el dni de su propietario
     * @param dni Dni del propietario
     * @return List<Vehiculo>
     */
    override fun findByDniPropietario(dni: String): List<Vehiculo> {
        DatabaseManager.open()
        val vehiculos = mutableListOf<Vehiculo>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM vehiculo WHERE dni_propietario = ?"
            val result = db.select(sql, dni)
            result?.let{
                while(result.next()){
                    val vehiculo = Vehiculo(
                        id = result.getInt("id"),
                        marca = result.getString("marca"),
                        modelo = result.getString("modelo"),
                        matricula = result.getString("matricula"),
                        fechaMatriculacion = dateFormat.parse(result.getString("fecha_matriculacion")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        fechaUltimaRevision = dateFormat.parse(result.getString("fecha_ultima_revision")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        dniPropietario = result.getString("dni_propietario"),
                    )
                    vehiculos.add(vehiculo)
                }
            }
        }
        return vehiculos
    }

    /**
     * Función que devuelve todos los vehiculos
     * @return List<Vehiculo>
     */
    override fun findAll(): List<Vehiculo> {
        DatabaseManager.open()
        val vehiculos = mutableListOf<Vehiculo>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM vehiculo"
            val result = db.select(sql)
            result?.let{
                while(result.next()){
                    val vehiculo = Vehiculo(
                        id = result.getInt("id"),
                        marca = result.getString("marca"),
                        modelo = result.getString("modelo"),
                        matricula = result.getString("matricula"),
                        fechaMatriculacion = dateFormat.parse(result.getString("fecha_matriculacion")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        fechaUltimaRevision = dateFormat.parse(result.getString("fecha_ultima_revision")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        dniPropietario = result.getString("dni_propietario"),
                    )
                    vehiculos.add(vehiculo)
                }
            }
        }
        return vehiculos
    }

    /**
     * Función que devuelve un vehiculo según su id
     * @param id Id del vehiculo
     * @return Vehiculo
     */
    override fun findById(id: Long): Vehiculo? {
        DatabaseManager.open()
        var vehiculo: Vehiculo? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM vehiculo WHERE id = ?"
            val result = db.select(sql, id)
            result?.let{
                while(result.next()){
                    vehiculo = Vehiculo(
                        id = result.getInt("id"),
                        marca = result.getString("marca"),
                        modelo = result.getString("modelo"),
                        matricula = result.getString("matricula"),
                        fechaMatriculacion = dateFormat.parse(result.getString("fecha_matriculacion")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        fechaUltimaRevision = dateFormat.parse(result.getString("fecha_ultima_revision")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        dniPropietario = result.getString("dni_propietario"),
                    )
                }
            }
        }
        return vehiculo
    }

    /**
     * Función que guarda un vehiculo
     * @param entity Vehiculo a guardar
     * @return Vehiculo
     */
    override fun save(entity: Vehiculo): Vehiculo {
        DatabaseManager.open()
        var vehiculo: Vehiculo? = null
        DatabaseManager.use{ db ->
            val sql = "INSERT INTO vehiculo (marca, modelo, matricula, fecha_matriculacion, fecha_ultima_revision, dni_propietario) VALUES (?, ?, ?, ?, ?, ?)"
            val result = db.update(sql, entity.marca, entity.modelo, entity.matricula, entity.fechaMatriculacion, entity.fechaUltimaRevision, entity.dniPropietario)
            if(result != null){
                vehiculo = entity
            }
        }
        return vehiculo!!
    }


    /**
     * Función que actualiza un vehiculo
     * @param entity Vehiculo a actualizar
     * @return Vehiculo
     */
    override fun update(entity: Vehiculo): Vehiculo {
        DatabaseManager.open()
        var vehiculo: Vehiculo? = null
        DatabaseManager.use{ db ->
            val sql = "UPDATE vehiculo SET marca = ?, modelo = ?, matricula = ?, fecha_matriculacion = ?, fecha_ultima_revision = ?, dni_propietario = ? WHERE id = ?"
            val result = db.update(sql, entity.marca, entity.modelo, entity.matricula, entity.fechaMatriculacion, entity.fechaUltimaRevision, entity.dniPropietario, entity.id)
            if(result != null){
                vehiculo = entity
            }
        }
        return vehiculo!!
    }

    /**
     * Función que elimina un vehiculo
     * @param entity Vehiculo a eliminar
     * @return Boolean
     */
    override fun delete(entity: Vehiculo): Boolean {
        DatabaseManager.open()
        var deleted = false
        DatabaseManager.use{ db ->
            val sql = "DELETE FROM vehiculo WHERE id = ?"
            val result = db.update(sql, entity.id)
            if(result == 1){
                deleted = true
            }
        }
        return deleted
    }

}