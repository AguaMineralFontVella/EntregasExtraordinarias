package es.sergiop.pepilandaSpringJPA.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto PropietarioException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class PropietarioException(message: String): RuntimeException(message) {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoValido(message: String) : PropietarioException(" ERROR: Propietario no válido: $message")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoGuardado(message: String) : PropietarioException(" ERROR: Propietario no guardado: $message")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class NoEncontrado(message: String) : PropietarioException(" ERROR: Propietario no encontrado: $message")
}