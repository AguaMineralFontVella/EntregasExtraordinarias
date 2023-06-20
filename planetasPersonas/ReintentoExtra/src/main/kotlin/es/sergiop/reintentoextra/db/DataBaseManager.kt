package es.sergiop.reintentoextra.db

import es.sergiop.reintentoextra.config.AppConfig
import mu.KotlinLogging
import org.apache.ibatis.jdbc.ScriptRunner
import java.io.*
import java.sql.*

private val logger = KotlinLogging.logger {}

object DataBaseManager : Closeable {

    private lateinit var serverUrl: String
    private lateinit var serverPort: String
    private lateinit var dataBaseName: String
    private lateinit var user: String
    private lateinit var password: String

    private var jdbcDriver: String = "org.h2.Driver"

    private var connection: Connection? = null
    private var preparedStatement: PreparedStatement? = null
    private val db: Connection get() = DriverManager.getConnection(AppConfig.dbUrl)


    init {
        logger.info { "Inicializando DatabaseManager" }
        initConfig()

        if(AppConfig.dbDropTables.toBoolean()){
            logger.info { "Eliminando tablas" }
            dropTables()
        }
    }

    @Throws(SQLException::class)
    private fun dropTables() {
        val sql = "DROP TABLE IF EXISTS PLANETAS"
        db.use {
            it.prepareStatement(sql).execute()
        }
    }

    private fun initConfig() {
        serverUrl = AppConfig.serverUrl
        serverPort = AppConfig.serverPort
        dataBaseName = AppConfig.dataBaseName
        jdbcDriver = AppConfig.jdbcDriver
        user = AppConfig.user
        password = AppConfig.password

        logger.debug { "Configuración de acceso a la Base de Datos cargada" }
    }

    @Throws(SQLException::class)
    fun open() {
        val url = "jdbc:h2:mem:${this.dataBaseName};DB_CLOSE_DELAY=-1"
        connection = DriverManager.getConnection(url, user, password)
        logger.info { "Conexión a la Base de Datos establecida: $url" }
    }

    @Throws(SQLException::class)
    override fun close() {
        preparedStatement?.close()
        connection?.close()
        logger.info { "Conexión a la Base de Datos cerrada" }
    }

    @Throws(SQLException::class)
    private fun executeQuery(querySQL: String, vararg params: Any?): ResultSet? {
        preparedStatement = connection?.prepareStatement(querySQL)
        return preparedStatement?.let {
            for (i in params.indices) {
                it.setObject(i + 1, params[i])
            }
            logger.debug { "Ejecutando consulta: $querySQL con parámetros: ${params.contentToString()}" }
            it.executeQuery()
        }
    }

    @Throws(SQLException::class)
    fun select(querySQL: String, vararg params: Any?): ResultSet? {
        return executeQuery(querySQL, *params)
    }

    @Throws(SQLException::class)
    fun select(querySQL: String, limit: Int, offset: Int, vararg params: Any?): ResultSet? {
        val query = "$querySQL LIMIT $limit OFFSET $offset"
        return executeQuery(query, *params)
    }

    @Throws(SQLException::class)
    fun insert(insertSQL: String, vararg params: Any?): Int {
        return updateQuery(insertSQL, *params)
    }

    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg params: Any?): Int {
        return updateQuery(updateSQL, *params)
    }


    @Throws(SQLException::class)
    fun delete(deleteSQL: String, vararg params: Any?): Int {
        return updateQuery(deleteSQL, *params)
    }

    @Throws(SQLException::class)
    private fun updateQuery(genericSQL: String, vararg params: Any?): Int {
        preparedStatement = connection?.prepareStatement(genericSQL)
        return preparedStatement?.let {
            for (i in params.indices) {
                preparedStatement!!.setObject(i + 1, params[i])
            }
            logger.debug { "Ejecutando consulta: $genericSQL con parámetros: ${params.contentToString()}" }
            it.executeUpdate()
        } ?: 0

    }

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
}