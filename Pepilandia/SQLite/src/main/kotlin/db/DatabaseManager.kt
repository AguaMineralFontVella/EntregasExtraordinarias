package db

import backup.BackupController.backupCita
import backup.BackupController.backupInforme
import backup.BackupController.backupPropietario
import backup.BackupController.backupTrabajador
import backup.BackupController.backupVehiculo
import com.github.michaelbull.result.*
import config.AppConfig
import mu.KotlinLogging
import org.apache.ibatis.jdbc.ScriptRunner
import java.io.*
import java.sql.*
import java.util.*

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que gestiona la base de datos
 * @property connection Connection?
 * @property preparedStatement PreparedStatement?
 * @property db Connection
 */
object DatabaseManager : Closeable {

    private var connection: Connection? = null
    private var preparedStatement: PreparedStatement? = null
    private val db: Connection get() = DriverManager.getConnection(AppConfig.dbUrl)

    init {
        logger.info { "Inicializando DatabaseManager" }

        if(AppConfig.dbDropTables.toBoolean()){
            logger.info { "Eliminando tablas" }
            dropTables()
        }
    }

    /**
     * Función que abre la conexión con la base de datos
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun open(){
        if (connection != null && !connection!!.isClosed) {
            logger.debug { "Conexión a la Base de Datos ya establecida" }
            return
        }
        connection = DriverManager.getConnection(AppConfig.dbUrl, AppConfig.dbUser, AppConfig.dbPassword)
        logger.info { "Conexión establecida con la base de datos: ${AppConfig.dbUrl}" }
    }

    /**
     * Función que cierra la conexión con la base de datos
     * @throws SQLException
     */
    @Throws(SQLException::class)
    override fun close(){
        preparedStatement?.close()
        connection?.close()
        logger.info { "Conexión con la base de datos cerrada" }
    }

    /**
     * Función que elimina las tablas de la base de datos
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun dropTables(){
        val sql = "DROP TABLE IF EXISTS Cita"
        db.use { it.prepareStatement(sql).execute() }
        val sql2 = "DROP TABLE IF EXISTS Vehiculo"
        db.use { it.prepareStatement(sql2).execute() }
        val sql3 = "DROP TABLE IF EXISTS Trabajador"
        db.use { it.prepareStatement(sql3).execute() }
        val sql4 = "DROP TABLE IF EXISTS Informe"
        db.use { it.prepareStatement(sql4).execute() }
        val sql5 = "DROP TABLE IF EXISTS Especialidad"
        db.use { it.prepareStatement(sql5).execute() }
        val sql6 = "DROP TABLE IF EXISTS Propietario"
        db.use { it.prepareStatement(sql6).execute() }
        logger.info { "Tablas eliminadas" }
    }

    /**
     * Función que ejecuta una consulta en la base de datos
     * @throws SQLException
     */
    @Throws(SQLException::class)
    private fun executeQuery(querySQL: String, vararg params: Any?): ResultSet? {
        preparedStatement = connection?.prepareStatement(querySQL)
        return preparedStatement?.let {
            for (i in params.indices) {
                it.setObject(i + 1, params[i])
            }
            logger.info { "Ejecutando consulta: $querySQL con parámetros: ${params.contentToString()}" }
            it.executeQuery()

        }
    }

    /**
     * Función que ejecuta una consulta en la base de datos de tipo SELECT
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, vararg params: Any?): ResultSet? {
        return executeQuery(querySQL, *params)
    }

    /**
     * Función que ejecuta una consulta en la base de datos de tipo UPDATE
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg params: Any?): Int {
        return updateQuery(updateSQL, *params)
    }

    /**
     * Función que ejecuta una consulta en la base de datos de tipo UPDATE
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun updateQuery(genericSQL: String, vararg params: Any?): Int {
        preparedStatement = connection?.prepareStatement(genericSQL)
        return preparedStatement?.let {
            for (i in params.indices) {
                preparedStatement!!.setObject(i + 1, params[i])
            }
            logger.info { "Ejecutando consulta: $genericSQL con parámetros: ${params.contentToString()}" }
            it.executeUpdate()
        } ?: 0

    }

    /**
     * Función que inserta en la base de datos las tablas necesarias para el funcionamiento de la aplicación desde un archivo .sql
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun initData() {
        open()
        val sqlFileResource = ClassLoader.getSystemResource("init.sql").file
        if (sqlFileResource == null) {
            logger.error { "Archivo .sql inaccesible" }
            return
        }
        try {
            val sr = ScriptRunner(connection)
            val reader: Reader = BufferedReader(FileReader(sqlFileResource))
            sr.runScript(reader)
            logger.info { "Datos iniciales cargados" }

        } catch (e: FileNotFoundException) {
            logger.error { "Archivo .sql no encontrado" }
        }
    }

    /**
     * Función que inserta en la base de datos los datos de los propietarios desde un archivo .csv
     * @param archivoCSV String
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun loadFromCSVPropietario(archivoCSV: String) {
        open()
        logger.info { "Cargando datos de propietario desde $archivoCSV" }
        val sql = "INSERT INTO propietario (id, dni, nombre, apellidos, telefono) VALUES (?, ?, ?, ?, ?)"
        val statement = connection?.prepareStatement(sql)

        BufferedReader(FileReader(archivoCSV)).use { br ->
            var line: String?
            var isFirstLine = true // Ignorar la primera línea (encabezados)

            while (br.readLine().also { line = it } != null) {
                if (isFirstLine) {
                    isFirstLine = false
                    continue
                }

                val values = line?.split(";") ?: continue
                val id = values[0].toIntOrNull() ?: continue
                val dni = values[1]
                val nombre = values[2]
                val apellidos = values[3]
                val telefono = values[4]

                statement?.setInt(1, id)
                statement?.setString(2, dni)
                statement?.setString(3, nombre)
                statement?.setString(4, apellidos)
                statement?.setString(5, telefono)

                statement?.executeUpdate()
            }
        }
        logger.info { "Datos de propietario cargados" }
    }

    /**
     * Función que inserta en la base de datos los datos de los trabajadores desde un archivo .csv
     * @param archivoCSV String
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun loadFromCSVTrabajador(archivoCSV: String){
        open()
        logger.info { "Cargando datos de trabajador desde $archivoCSV" }
        val sql = "INSERT INTO trabajador (nombre, telefono, email, nombreUsuario, password, especialidad_trabajador, isResponsable, salario, fecha_contratacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
        val statement = connection?.prepareStatement(sql)

        BufferedReader(FileReader(archivoCSV)).use { br ->
            var line: String?
            var isFirstLine = true // Ignorar la primera línea (encabezados)

            while (br.readLine().also { line = it } != null) {
                if (isFirstLine) {
                    isFirstLine = false
                    continue
                }

                val values = line?.split(";") ?: continue
                val nombre = values[1]
                val telefono = values[2]
                val email = values[3]
                val nombreUsuario = values[4]
                val password = values[5]
                val especialidad = values[6]
                val isResponsable = values[7].toBoolean()
                val salario = values[8].toDoubleOrNull() ?: continue
                val fechaContratacion = values[9]

                statement?.setString(1, nombre)
                statement?.setString(2, telefono)
                statement?.setString(3, email)
                statement?.setString(4, nombreUsuario)
                statement?.setString(5, password)
                statement?.setString(6, especialidad)
                statement?.setBoolean(7, isResponsable)
                statement?.setDouble(8, salario)
                statement?.setString(9, fechaContratacion)

                statement?.executeUpdate()
            }
        }
        logger.info { "Datos de trabajador cargados" }
    }

    /**
     * Función que inserta en la base de datos los datos de los vehículos desde un archivo .csv
     * @param archivoCSV String
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun loadFromCSVVehiculo(archivoCSV: String){
        open()
        logger.info { "Cargando datos de vehiculo desde $archivoCSV" }
        val sql = "INSERT INTO vehiculo (id, marca, modelo, matricula, fecha_matriculacion, fecha_ultima_revision, dni_propietario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"
        val statement = connection?.prepareStatement(sql)

        BufferedReader(FileReader(archivoCSV)).use { br ->
            var line: String?
            var isFirstLine = true // Ignorar la primera línea (encabezados)

            while (br.readLine().also { line = it } != null) {
                if (isFirstLine) {
                    isFirstLine = false
                    continue
                }

                val values = line?.split(";") ?: continue
                val id = values[0].toIntOrNull() ?: continue
                val marca = values[1]
                val modelo = values[2]
                val matricula = values[3]
                val fechaMatriculacion = values[4]
                val fechaUltimaRevision = values[5]
                val dniPropietario = values[6]

                statement?.setInt(1, id)
                statement?.setString(2, marca)
                statement?.setString(3, modelo)
                statement?.setString(4, matricula)
                statement?.setString(5, fechaMatriculacion)
                statement?.setString(6, fechaUltimaRevision)
                statement?.setString(7, dniPropietario)

                statement?.executeUpdate()
            }
        }
        logger.info { "Datos de vehiculo cargados" }
    }

    /**
     * Función que realiza un backup de la base de datos
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun backup(){
        open()

        logger.info { "Realizando backup de la base de datos" }

        val resource = ClassLoader.getSystemResource("config.properties")

        backupCita(resource)
        backupInforme(resource)
        backupVehiculo(resource)
        backupPropietario(resource)
        backupTrabajador(resource)

    }

}