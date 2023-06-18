package es.sergiop.pepilandaSpringJPA.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto CitaException para el manejo de excepciones
 * @param message Mensaje de error
 */
sealed class CitaException(message: String): RuntimeException(message) {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoValido(message: String) : CitaException(" ERROR: Cita no válida: $message")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoGuardado(message: String) : CitaException(" ERROR: Cita no guardada: $message")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class NoEncontrado(message: String) : CitaException(" ERROR: Cita no encontrada: $message")
}