package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto TrabajadorException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class TrabajadorException(message: String): RuntimeException(message) {
    class NoValido(message: String) : TrabajadorException(" ERROR: Trabajador no válido: $message")
    class NoGuardado(message: String) : TrabajadorException(" ERROR: Trabajador no guardado: $message")
    class NoEncontrado(message: String) : TrabajadorException(" ERROR: Trabajador no encontrado: $message")
}