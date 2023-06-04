package repository.informeRepository

import db.DatabaseManager
import models.Informe

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que implementa la interfaz InformeRepository y que se encarga de gestionar los informes
 * @property findByIsFavorable Función que devuelve todos los informes favorables
 * @property findAll Función que devuelve todos los informes
 * @property findById Función que devuelve un informe según su id
 * @property save Función que guarda un informe
 * @property update Función que actualiza un informe
 * @property delete Función que elimina un informe
 */
class InformeRepositoryImpl : InformeRepository {

    /**
     * Función que devuelve todos los informes favorables
     * @return Lista de informes
     */
    override fun findByIsFavorable(): List<Informe> {
        DatabaseManager.open()
        val informes = mutableListOf<Informe>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM informe WHERE isFavorable = true"
            val result = db.select(sql)
            result?.let{
                while(result.next()){
                    val informe = Informe(
                        id = result.getInt("id"),
                        frenado = result.getDouble("frenado"),
                        frenadoIsApto = result.getBoolean("frenadoIsApto"),
                        contaminacion = result.getDouble("contaminacion"),
                        lucesIsApto = result.getBoolean("lucesIsApto"),
                        idCita = result.getInt("id_cita"),
                        idTrabajador = result.getInt("id_trabajador"),
                        idVehiculo = result.getInt("id_vehiculo"),
                        dniPropietario = result.getString("dni_propietario"),
                        isFavorable = result.getInt("isFavorable")
                    )
                    informes.add(informe)
                }
            }
        }
        return informes
    }

    /**
     * Función que devuelve todos los informes
     * @return Lista de informes
     */
    override fun findAll(): List<Informe> {
        DatabaseManager.open()
        val informes = mutableListOf<Informe>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM informe"
            val result = db.select(sql)
            result?.let{
                while(result.next()){
                    val informe = Informe(
                        id = result.getInt("id"),
                        frenado = result.getDouble("frenado"),
                        frenadoIsApto = result.getBoolean("frenadoIsApto"),
                        contaminacion = result.getDouble("contaminacion"),
                        lucesIsApto = result.getBoolean("lucesIsApto"),
                        idCita = result.getInt("id_cita"),
                        idTrabajador = result.getInt("id_trabajador"),
                        idVehiculo = result.getInt("id_vehiculo"),
                        dniPropietario = result.getString("dni_propietario"),
                        isFavorable = result.getInt("isFavorable")
                    )
                    informes.add(informe)
                }
            }
        }
        return informes
    }

    /**
     * Función que devuelve un informe según su id
     * @param id Id del informe
     * @return Informe
     */
    override fun findById(id: Long): Informe? {
        DatabaseManager.open()
        var informe: Informe? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM informe WHERE id = ?"
            val result = db.select(sql, id)
            result?.let{
                while(result.next()){
                    informe = Informe(
                        id = result.getInt("id"),
                        frenado = result.getDouble("frenado"),
                        frenadoIsApto = result.getBoolean("frenadoIsApto"),
                        contaminacion = result.getDouble("contaminacion"),
                        lucesIsApto = result.getBoolean("lucesIsApto"),
                        idCita = result.getInt("id_cita"),
                        idTrabajador = result.getInt("id_trabajador"),
                        idVehiculo = result.getInt("id_vehiculo"),
                        dniPropietario = result.getString("dni_propietario"),
                        isFavorable = result.getInt("isFavorable")
                    )
                }
            }
        }
        return informe
    }

    /**
     * Función que guarda un informe
     * @param entity Informe
     * @return Informe
     */
    override fun save(entity: Informe): Informe {
        DatabaseManager.open()
        var informe: Informe? = null
        DatabaseManager.use{ db ->
            val sql = "INSERT INTO informe (frenado, frenadoIsApto, contaminacion, lucesIsApto, id_cita, id_trabajador, id_vehiculo, dni_propietario, isFavorable) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            val result = db.update(sql, entity.frenado, entity.frenadoIsApto, entity.contaminacion, entity.lucesIsApto, entity.idCita, entity.idTrabajador, entity.idVehiculo, entity.dniPropietario, entity.isFavorable)
            if(result != null){
                informe = entity
            }
        }
        return informe!!
    }

    /**
     * Función que actualiza un informe
     * @param entity Informe
     * @return Informe
     */
    override fun update(entity: Informe): Informe {
        DatabaseManager.open()
        var informe: Informe? = null
        DatabaseManager.use{ db ->
            val sql = "UPDATE informe SET frenado = ?, frenadoIsApto = ?, contaminacion = ?, lucesIsApto = ?, id_cita = ?, id_trabajador = ?, id_vehiculo = ?, dni_propietario = ?, isFavorable = ? WHERE id = ?"
            val result = db.update(sql, entity.frenado, entity.frenadoIsApto, entity.contaminacion, entity.lucesIsApto, entity.idCita, entity.idTrabajador, entity.idVehiculo, entity.dniPropietario, entity.isFavorable, entity.id)
            if(result == 1){
                informe = entity
            }
        }
        return informe!!
    }

    /**
     * Función que elimina un informe
     * @param entity Informe
     * @return Boolean
     */
    override fun delete(entity: Informe): Boolean {
        DatabaseManager.open()
        var deleted = false
        DatabaseManager.use{ db ->
            val sql = "DELETE FROM informe WHERE id = ?"
            val result = db.update(sql, entity.id)
            if(result == 1){
                deleted = true
            }
        }
        return deleted
    }
}