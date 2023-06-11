package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.PropietarioCreateDto
import es.sergiop.pepilandaSpringJPA.dto.PropietarioDto
import es.sergiop.pepilandaSpringJPA.models.Propietario

fun Propietario.toPropietarioDto(): PropietarioDto {
    return PropietarioDto(
        id = id,
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}

fun PropietarioDto.toPropietario(): Propietario{
    return Propietario(
        id = id,
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}

fun PropietarioCreateDto.toPropietario(): Propietario{
    return Propietario(
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}