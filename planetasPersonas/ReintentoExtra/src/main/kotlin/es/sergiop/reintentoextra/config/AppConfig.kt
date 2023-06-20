package es.sergiop.reintentoextra.config

import java.io.FileInputStream
import java.util.*

object AppConfig {

    lateinit var serverUrl: String
    lateinit var serverPort: String
    lateinit var dataBaseName : String
    lateinit var jdbcDriver : String
    lateinit var dbUrl : String
    lateinit var user : String
    lateinit var password: String
    lateinit var dbDropTables: String

    /**
     * Función que inicializa la configuración de la aplicación
     */
    fun initConfig(){

        val propertiesFile = ClassLoader.getSystemResource("config.properties").file
        println(propertiesFile.toString())

            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            serverUrl = properties.getProperty("serverUrl")
            serverPort = properties.getProperty("serverPort")
            dataBaseName = properties.getProperty("dataBaseName")
            jdbcDriver = properties.getProperty("jdbcDriver")
            user = properties.getProperty("user")
            password = properties.getProperty("password")
            dbDropTables = properties.getProperty("dbDropTables")
            dbUrl = properties.getProperty("dbUrl")

    }
}


