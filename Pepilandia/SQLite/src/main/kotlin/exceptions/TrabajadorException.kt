package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de los trabajadores
 * @property message String
 */
sealed class TrabajadorException(val message: String) {
    class NoValido(message: String) : TrabajadorException(" ERROR: Trabajador no válido: $message")
    class NoGuardado(message: String) : TrabajadorException(" ERROR: Trabajador no guardado: $message")
    class NoEncontrado(message: String) : TrabajadorException(" ERROR: Trabajador no encontrado: $message")
}