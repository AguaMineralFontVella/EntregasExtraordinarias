package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de los propietarios
 * @property message String
 */
sealed class PropietarioException(val message: String) {
    class NoValido(message: String) : PropietarioException(" ERROR: Propietario no válido: $message")
    class NoGuardado(message: String) : PropietarioException(" ERROR: Propietario no guardado: $message")
    class NoEncontrado(message: String) : PropietarioException(" ERROR: Propietario no encontrado: $message")
}