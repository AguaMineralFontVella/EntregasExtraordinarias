package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de los informes
 * @property message String
 */
sealed class InformeException(val message: String) {
    class NoValido(message: String) : InformeException(" ERROR: Informe no válido: $message")
    class NoGuardado(message: String) : InformeException(" ERROR: Informe no guardado: $message")
    class NoEncontrado(message: String) : InformeException(" ERROR: Informe no encontrado: $message")
}