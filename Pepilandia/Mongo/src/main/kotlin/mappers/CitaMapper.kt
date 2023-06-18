package mappers

import dto.CitaCreateDto
import dto.CitaDto
import models.Cita
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
        idTrabajador = idTrabajador,
        idVehiculo = idVehiculo,
        fecha = fecha.toString(),
        hora = hora
    )
}

/**
 * Funcion para convertir un CitaCreateDto a una Cita
 * @return Cita
 */
fun CitaCreateDto.toCita(horaAleatoria: String): Cita {
    return Cita(
        idTrabajador = idTrabajador,
        idVehiculo = idVehiculo,
        fecha = LocalDate.parse(fecha),
        hora = horaAleatoria
    )
}