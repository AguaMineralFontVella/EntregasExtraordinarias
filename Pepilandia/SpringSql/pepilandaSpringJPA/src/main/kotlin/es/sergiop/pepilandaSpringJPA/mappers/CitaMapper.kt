package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.CitaCreateDto
import es.sergiop.pepilandaSpringJPA.dto.CitaDto
import es.sergiop.pepilandaSpringJPA.models.Cita
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir una Cita a un CitaDto
 * @return CitaDto
 */
fun Cita.toCitaDto(): CitaDto {
    return CitaDto(
        id = id,
        idTrabajador = idTrabajador.toString(),
        idVehiculo = idVehiculo.toString(),
        fecha = fecha.toString(),
        hora = hora
    )
}

/**
 * Funcion para convertir una CitaDto a una Cita
 * @return Cita
 */
fun CitaDto.toCita(): Cita {
    return Cita(
        id = id,
        idTrabajador = idTrabajador.toInt(),
        idVehiculo = idVehiculo.toInt(),
        fecha = LocalDate.parse(fecha),
        hora = hora
    )
}

/**
 * Funcion para convertir una CitaCreateDto en una Cita
 * @return Cita
 */
fun CitaCreateDto.toCita(): Cita {
    return Cita(
        idTrabajador = idTrabajador.toInt(),
        idVehiculo = idVehiculo.toInt(),
        fecha = LocalDate.parse(fecha),
        hora = hora
    )
}