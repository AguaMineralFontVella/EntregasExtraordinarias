package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto InformeException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class InformeException(message: String): RuntimeException(message) {
    class NoValido(message: String) : InformeException(" ERROR: Informe no válido: $message")
    class NoGuardado(message: String) : InformeException(" ERROR: Informe no guardado: $message")
    class NoEncontrado(message: String) : InformeException(" ERROR: Informe no encontrado: $message")
}