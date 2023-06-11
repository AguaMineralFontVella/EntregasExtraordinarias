package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.VehiculoCreateDto
import es.sergiop.pepilandaSpringJPA.dto.VehiculoDto
import es.sergiop.pepilandaSpringJPA.models.Vehiculo
import java.time.LocalDate

fun Vehiculo.toVehiculoDto(): VehiculoDto{
    return VehiculoDto(
        id = id,
        marca = marca,
        modelo = modelo,
        matricula = matricula,
        fechaMatriculacion = fechaMatriculacion.toString(),
        fechaUltimaRevision = fechaUltimaRevision.toString(),
        dniPropietario = dniPropietario
    )
}

fun VehiculoDto.toVehiculo(): Vehiculo{
    return Vehiculo(
        id = id,
        marca = marca,
        modelo = modelo,
        matricula = matricula,
        fechaMatriculacion = LocalDate.parse(fechaMatriculacion),
        fechaUltimaRevision = LocalDate.parse(fechaUltimaRevision),
        dniPropietario = dniPropietario
    )
}

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