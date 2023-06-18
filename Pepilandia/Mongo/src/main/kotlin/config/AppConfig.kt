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

    lateinit var URL : String
    lateinit var DATABASE : String
    lateinit var USERNAME: String
    lateinit var PASSWORD: String

    /**
     * Función que inicializa la configuración de la aplicación
     */
    fun initConfig(){

        val propertiesFile = ClassLoader.getSystemResource("config.properties").file
        println(propertiesFile.toString())

            val properties = Properties()
            properties.load(FileInputStream(propertiesFile))

            URL = properties.getProperty("URL")
            DATABASE = properties.getProperty("DATABASE")
            USERNAME = properties.getProperty("USERNAME")
            PASSWORD = properties.getProperty("PASSWORD")


    }
}


