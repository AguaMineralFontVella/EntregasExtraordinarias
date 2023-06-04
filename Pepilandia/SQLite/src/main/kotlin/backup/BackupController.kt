package backup

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import config.AppConfig
import java.nio.file.Files
import db.DatabaseManager
import exceptions.BackupException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.*
import mu.KotlinLogging
import java.io.File
import java.net.URL
import java.nio.file.Paths

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

object BackupController {

    /**
     * Realiza un backup de la base de datos para la tabla Cita
     * @param resource URL del recurso donde se guardará el backup
     * @return Result<Boolean, BackupException>
     */
    fun backupCita(resource: URL): Result<Boolean, BackupException> {

        val query = "SELECT * FROM cita"
        val data = mutableListOf<CitaDTO>()
        val cursor = DatabaseManager.select(query)
        while (cursor?.next() == true) {
            val id = cursor.getInt("id")
            val idTrabajador = cursor.getString("id_trabajador")
            val idVehiculo = cursor.getString("id_vehiculo")
            val fechaHora = cursor.getString("fecha")
            val hora = cursor.getString("hora")
            val cita = CitaDTO(id, idTrabajador, idVehiculo, fechaHora, hora)
            data.add(cita)
        }

        val backupFilePath = File((Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.backupCitaFilePath).toString())

        logger.info { "Guardando backup de las citas en: ${backupFilePath.absolutePath}" }
        Files.createDirectories(Paths.get(backupFilePath.parent))
        return if (backupFilePath.exists()) {
            val json3 = Json { prettyPrint = true }
            backupFilePath.writeText(json3.encodeToString(data))
            logger.info { "Backup-Citas realizado con éxito" }
            Ok(true)
        } else {
            //crea el directorio
            if (backupFilePath.createNewFile()) {
                val json3 = Json { prettyPrint = true }
                backupFilePath.writeText(json3.encodeToString(data))
                logger.info { "Backup-Citas realizado con éxito" }
                Ok(true)
            } else {
                val exception = BackupException.NoGuardado("No se ha podido crear el archivo de backup de citas")
                logger.error { exception.message }
                Err(exception)
            }
        }
    }

    /**
     * Realiza un backup de la base de datos para la tabla Informe
     * @param resource URL del recurso donde se guardará el backup
     * @return Result<Boolean, BackupException>
     */
    fun backupInforme(resource: URL): Result<Boolean, BackupException>{
        val query = "SELECT * FROM informe"
        val data = mutableListOf<Informe>()
        val cursor = DatabaseManager.select(query)
        while (cursor?.next() == true) {
            val id = cursor.getInt("id")
            val frenado = cursor.getDouble("frenado")
            val frenadoIsApto = cursor.getBoolean("frenadoIsApto")
            val contaminacion = cursor.getDouble("contaminacion")
            val lucesIsApto = cursor.getBoolean("lucesIsApto")
            val idCita = cursor.getInt("id_cita")
            val idTrabajador = cursor.getInt("id_trabajador")
            val idVehiculo = cursor.getInt("id_vehiculo")
            val dniPropietario = cursor.getString("dni_propietario")
            val isFavorable = cursor.getInt("isFavorable")
            val cita = Informe(id, frenado, frenadoIsApto, contaminacion, lucesIsApto, idCita, idTrabajador, idVehiculo, dniPropietario, isFavorable)
            data.add(cita)
        }

        val backupFilePath = File((Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.backupInformeFilePath).toString())

        logger.info { "Guardando backup de los informes en: ${backupFilePath.absolutePath}" }
        return if (backupFilePath.exists()) {
            val json3 = Json { prettyPrint = true }
            backupFilePath.writeText(json3.encodeToString(data))
            logger.info { "Backup-Informes realizado con éxito" }
            Ok(true)
        } else {
            if (backupFilePath.createNewFile()) {
                val json3 = Json { prettyPrint = true }
                backupFilePath.writeText(json3.encodeToString(data))
                logger.info { "Backup-Informes realizado con éxito" }
                Ok(true)
            } else {
                val exception = BackupException.NoGuardado("No se ha podido crear el archivo de backup de informes")
                logger.error { exception.message }
                Err(exception)
            }
        }
    }

    /**
     * Realiza un backup de la base de datos para la tabla Vehículo
     * @param resource URL del recurso donde se guardará el backup
     * @return Result<Boolean, BackupException>
     */
    fun backupVehiculo(resource: URL): Result<Boolean, BackupException> {

        val query = "SELECT * FROM vehiculo"
        val data = mutableListOf<VehiculoDTO>()
        val cursor = DatabaseManager.select(query)
        while (cursor?.next() == true) {
            val id = cursor.getInt("id")
            val marca = cursor.getString("marca")
            val modelo = cursor.getString("modelo")
            val matricula = cursor.getString("matricula")
            val fechaMatriculacion = cursor.getString("fecha_matriculacion")
            val fechaUltimaRevision = cursor.getString("fecha_ultima_revision")
            val dniPropietario = cursor.getString("dni_propietario")
            val vehiculo = VehiculoDTO(id, marca, modelo, matricula, fechaMatriculacion, fechaUltimaRevision, dniPropietario)
            data.add(vehiculo)
        }

        val backupFilePath = File((Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.backupVehiculoFilePath).toString())

        logger.info { "Guardando backup de los vehículos en: ${backupFilePath.absolutePath}" }
        return if (backupFilePath.exists()) {
            val json3 = Json { prettyPrint = true }
            backupFilePath.writeText(json3.encodeToString(data))
            logger.info { "Backup-Vehiculos realizado con éxito" }
            Ok(true)
        } else {
            if (backupFilePath.createNewFile()) {
                val json3 = Json { prettyPrint = true }
                backupFilePath.writeText(json3.encodeToString(data))
                logger.info { "Backup-Vehiculos realizado con éxito" }
                Ok(true)
            } else {
                val exception = BackupException.NoGuardado("No se ha podido crear el archivo de backup de vehículos")
                logger.error { exception.message }
                Err(exception)
            }
        }
    }

    /**
     * Realiza un backup de la base de datos para la tabla Propietario
     * @param resource URL del recurso donde se guardará el backup
     * @return Result<Boolean, BackupException>
     */
    fun backupPropietario(resource: URL): Result<Boolean, BackupException> {

        val query = "SELECT * FROM propietario"
        val data = mutableListOf<Propietario>()
        val cursor = DatabaseManager.select(query)
        while (cursor?.next() == true) {
            val id = cursor.getInt("id")
            val dni = cursor.getString("dni")
            val nombre = cursor.getString("nombre")
            val apellidos = cursor.getString("apellidos")
            val telefono = cursor.getString("telefono")
            val propietario = Propietario(id, dni, nombre, apellidos, telefono)
            data.add(propietario)
        }

        val backupFilePath = File((Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.backupPropietarioFilePath).toString())

        logger.info { "Guardando backup de los propietarios en: ${backupFilePath.absolutePath}" }
        return if (backupFilePath.exists()) {
            val json3 = Json { prettyPrint = true }
            backupFilePath.writeText(json3.encodeToString(data))
            logger.info { "Backup-Propietarios realizado con éxito" }
            Ok(true)
        } else {
            if (backupFilePath.createNewFile()) {
                val json3 = Json { prettyPrint = true }
                backupFilePath.writeText(json3.encodeToString(data))
                logger.info { "Backup-Propietarios realizado con éxito" }
                Ok(true)
            } else {
                val exception = BackupException.NoGuardado("No se ha podido crear el archivo de backup de propietarios")
                logger.error { exception.message }
                Err(exception)
            }
        }
    }

    /**
     * Realiza un backup de la base de datos para la tabla Trabajador
     * @param resource URL del recurso donde se guardará el backup
     * @return Result<Boolean, BackupException>
     */
    fun backupTrabajador(resource: URL): Result<Boolean, BackupException> {

        val query = "SELECT * FROM trabajador"
        val data = mutableListOf<TrabajadorDTO>()
        val cursor = DatabaseManager.select(query)
        while (cursor?.next() == true) {
            val id = cursor.getInt("id")
            val nombre = cursor.getString("nombre")
            val telefono = cursor.getString("telefono")
            val email = cursor.getString("email")
            val nombreUsuario = cursor.getString("nombreUsuario")
            val password = cursor.getString("password")
            val especialidad = cursor.getString("especialidad_trabajador")
            val isResponsable = cursor.getInt("isResponsable")
            val salario = cursor.getDouble("salario")
            val fechaContratacion = cursor.getString("fecha_contratacion")

            val trabajador = TrabajadorDTO(id, nombre, telefono, email, nombreUsuario, password, especialidad, isResponsable, salario, fechaContratacion)
            data.add(trabajador)
        }

        val backupFilePath = File((Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.backupTrabajadorFilePath).toString())

        logger.info { "Guardando backup de los trabajadores en: ${backupFilePath.absolutePath}" }
        return if (backupFilePath.exists()) {
            val json3 = Json { prettyPrint = true }
            backupFilePath.writeText(json3.encodeToString(data))
            logger.info { "Backup-Trabajadores realizado con éxito" }
            Ok(true)
        } else {
            if (backupFilePath.createNewFile()) {
                val json3 = Json { prettyPrint = true }
                backupFilePath.writeText(json3.encodeToString(data))
                logger.info { "Backup-Trabajadores realizado con éxito" }
                Ok(true)
            } else {
                val exception = BackupException.NoGuardado("No se ha podido crear el archivo de backup de trabajadores")
                logger.error { exception.message }
                Err(exception)
            }
        }
    }
}