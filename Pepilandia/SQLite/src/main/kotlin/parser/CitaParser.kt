package parser

import models.Cita
import models.CitaDTO

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Función que convierte un objeto Cita en un objeto CitaDTO
 * @return Objeto CitaDTO
 */
fun Cita.toCitaDTO(): CitaDTO{
    return CitaDTO(
        id = this.id,
        idTrabajador = this.idTrabajador.toString(),
        idVehiculo = this.idVehiculo.toString(),
        fecha = this.fecha.toString(),
        hora = this.hora,
    )
}
