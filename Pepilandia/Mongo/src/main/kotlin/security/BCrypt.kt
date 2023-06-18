package security

import com.toxicbakery.bcrypt.Bcrypt
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Objeto que se encarga de cifrar las contraseñas
 * @property cifrar Función que cifra una contraseña
 */
object BCrypt {

    /**
     * Función que cifra una contraseña
     * @param password Contraseña a cifrar
     * @return Contraseña cifrada
     */
    fun cifrar(password: String): String {

        val cifrated = Bcrypt.hash(password, 12)

        return if (Bcrypt.verify(password, cifrated)) {
            cifrated.toString()
        } else {
            logger.info { "Error al cifrar la contraseña" }
            "Error"
        }

    }

}


