package config

import java.io.FileInputStream
import java.util.*

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona la configuración de la aplicación
 */
object AppConfig {

    lateinit var dbName: String
    lateinit var dbPort: String
    lateinit var dbDriver : String
    lateinit var dbUrl : String
    lateinit var dbDropTables : String
    lateinit var dbUser: String
    lateinit var dbPassword: String
    lateinit var csvPropietarioPath: String
    lateinit var csvVehiculoPath: String
    lateinit var csvTrabajadorPath: String
    lateinit var backupCitaFilePath: String
    lateinit var backupInformeFilePath: String
    lateinit var backupPropietarioFilePath: String
    lateinit var backupTrabajadorFilePath: String
    lateinit var backupVehiculoFilePath: String

    /**
     * Función que inicializa la configuración de la aplicación
     */
    fun initConfig(){

        val propertiesFile = ClassLoader.getSystemResource("config.properties").file
        println(propertiesFile.toString())

            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            dbName = properties.getProperty("dbName")
            dbPort = properties.getProperty("dbPort")
            dbDriver = properties.getProperty("dbDriver")
            dbUrl = properties.getProperty("dbUrl")
            dbDropTables = properties.getProperty("dbDropTables")
            dbUser = properties.getProperty("dbUser")
            dbPassword = properties.getProperty("dbPassword")
            csvPropietarioPath = properties.getProperty("csvPropietarioPath")
            csvVehiculoPath = properties.getProperty("csvVehiculoPath")
            csvTrabajadorPath = properties.getProperty("csvTrabajadorPath")
            backupCitaFilePath = properties.getProperty("backupCitaFilePath")
            backupInformeFilePath = properties.getProperty("backupInformeFilePath")
            backupPropietarioFilePath = properties.getProperty("backupPropietarioFilePath")
            backupTrabajadorFilePath = properties.getProperty("backupTrabajadorFilePath")
            backupVehiculoFilePath = properties.getProperty("backupVehiculoFilePath")

    }
}


