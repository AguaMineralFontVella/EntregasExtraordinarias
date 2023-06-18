package mappers

import dto.PropietarioCreateDto
import models.Propietario


/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

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