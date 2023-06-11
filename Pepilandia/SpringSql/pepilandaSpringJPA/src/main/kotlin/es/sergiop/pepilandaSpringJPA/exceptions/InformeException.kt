package es.sergiop.pepilandaSpringJPA.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

sealed class InformeException(message: String): RuntimeException(message) {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoValido(message: String) : InformeException(" ERROR: Informe no válido: $message")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class NoGuardado(message: String) : InformeException(" ERROR: Informe no guardado: $message")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class NoEncontrado(message: String) : InformeException(" ERROR: Informe no encontrado: $message")
}