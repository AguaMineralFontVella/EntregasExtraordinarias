package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.CitaCreateDto
import es.sergiop.pepilandaSpringJPA.dto.CitaDto
import es.sergiop.pepilandaSpringJPA.models.Cita
import java.time.LocalDate

fun Cita.toCitaDto(): CitaDto {
    return CitaDto(
        id = id,
        idTrabajador = idTrabajador.toString(),
        idVehiculo = idVehiculo.toString(),
        fecha = fecha.toString(),
        hora = hora
    )
}

fun CitaDto.toCita(): Cita {
    return Cita(
        id = id,
        idTrabajador = idTrabajador.toInt(),
        idVehiculo = idVehiculo.toInt(),
        fecha = LocalDate.parse(fecha),
        hora = hora
    )
}

fun CitaCreateDto.toCita(): Cita {
    return Cita(
        idTrabajador = idTrabajador.toInt(),
        idVehiculo = idVehiculo.toInt(),
        fecha = LocalDate.parse(fecha),
        hora = hora
    )
}