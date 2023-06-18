package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto CitaException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class CitaException(message: String): RuntimeException(message) {
    class NoValido(message: String) : CitaException(" ERROR: Cita no válida: $message")
    class NoGuardado(message: String) : CitaException(" ERROR: Cita no guardada: $message")
    class NoEncontrado(message: String) : CitaException(" ERROR: Cita no encontrada: $message")
}