package mappers

import dto.VehiculoCreateDto
import models.Vehiculo
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir un VehiculoCreateDto a un Vehiculo
 * @return Vehiculo
 */
fun VehiculoCreateDto.toVehiculo(): Vehiculo{
    return Vehiculo(
        marca = marca,
        modelo = modelo,
        matricula = matricula,
        fechaMatriculacion = LocalDate.parse(fechaMatriculacion),
        fechaUltimaRevision = LocalDate.parse(fechaUltimaRevision),
        dniPropietario = dniPropietario
    )
}