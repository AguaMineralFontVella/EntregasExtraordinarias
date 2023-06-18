package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.PropietarioCreateDto
import es.sergiop.pepilandaSpringJPA.dto.PropietarioDto
import es.sergiop.pepilandaSpringJPA.models.Propietario

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir un Propietario a un PropietarioDto
 * @return PropietarioDto
 */
fun Propietario.toPropietarioDto(): PropietarioDto {
    return PropietarioDto(
        id = id,
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}

/**
 * Funcion para convertir un PropietarioDto a un Propietario
 * @return Propietario
 */
fun PropietarioDto.toPropietario(): Propietario{
    return Propietario(
        id = id,
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}

/**
 * Funcion para convertir un PropietarioCreateDto a un Propietario
 * @return Propietario
 */
fun PropietarioCreateDto.toPropietario(): Propietario{
    return Propietario(
        dni= dni,
        nombre = nombre,
        apellidos = apellidos,
        telefono = telefono,
    )
}