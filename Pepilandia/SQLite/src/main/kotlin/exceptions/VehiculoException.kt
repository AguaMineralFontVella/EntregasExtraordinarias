package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de los vehículos
 * @property message String
 */
sealed class VehiculoException(val message: String) {
    class NoValido(message: String) : VehiculoException(" ERROR: Vehículo no válido: $message")
    class NoGuardado(message: String) : VehiculoException(" ERROR: Vehículo no guardado: $message")
    class NoEncontrado(message: String) : VehiculoException(" ERROR: Vehículo no encontrado: $message")
}