package db

import config.AppConfig
import mu.KotlinLogging
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Objeto que gestiona la base de datos
 * @property mongoClient Cliente de la base de datos
 * @property database Base de datos
 */
object DataBaseManager {
    private var mongoClient: CoroutineClient
    var database: CoroutineDatabase

    init {
        logger.info { "Inicializando la base de datos MongoDB" }
        mongoClient = KMongo.createClient(AppConfig.URL).coroutine
        database = mongoClient.getDatabase(AppConfig.DATABASE)
    }
}