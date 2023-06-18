package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto PropietarioException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class PropietarioException(message: String): RuntimeException(message) {
    class NoValido(message: String) : PropietarioException(" ERROR: Propietario no válido: $message")
    class NoGuardado(message: String) : PropietarioException(" ERROR: Propietario no guardado: $message")
    class NoEncontrado(message: String) : PropietarioException(" ERROR: Propietario no encontrado: $message")
}