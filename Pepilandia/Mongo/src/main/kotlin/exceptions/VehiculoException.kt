package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto VehiculoException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class VehiculoException(message: String): RuntimeException(message) {
    class NoValido(message: String) : VehiculoException(" ERROR: Vehiculo no válido: $message")
    class NoGuardado(message: String) : VehiculoException(" ERROR: Vehiculo no guardado: $message")
    class NoEncontrado(message: String) : VehiculoException(" ERROR: Vehiculo no encontrado: $message")
}