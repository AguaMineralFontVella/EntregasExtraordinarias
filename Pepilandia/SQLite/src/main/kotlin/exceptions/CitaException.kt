package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de las citas
 * @property message String
 */
sealed class CitaException(val message: String) {
    class NoValido(message: String) : CitaException(" ERROR: Cita no válida: $message")
    class NoGuardado(message: String) : CitaException(" ERROR: Cita no guardada: $message")
    class NoEncontrado(message: String) : CitaException(" ERROR: Cita no encontrada: $message")
}