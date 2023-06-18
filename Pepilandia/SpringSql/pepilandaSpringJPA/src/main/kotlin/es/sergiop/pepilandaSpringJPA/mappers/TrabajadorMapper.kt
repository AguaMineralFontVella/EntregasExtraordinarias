package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.TrabajadorCreateDto
import es.sergiop.pepilandaSpringJPA.dto.TrabajadorDto
import es.sergiop.pepilandaSpringJPA.models.Trabajador
import es.sergiop.pepilandaSpringJPA.security.BCrypt.cifrar
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir un Trabajador a un TrabajadorDto
 * @return TrabajadorDto
 */
fun Trabajador.toTrabajadorDto(): TrabajadorDto {
    return TrabajadorDto(
        id = id,
        nombre = nombre,
        telefono = telefono,
        email = email,
        nombreUsuario = nombreUsuario,
        password = cifrar(password),
        especialidad = especialidad,
        isResponsable = isResponsable,
        salario = salario.toString(),
        fechaContratacion = fechaContratacion.toString()
    )
}

/**
 * Funcion para convertir un TrabajadorDto a un Trabajador
 * @return Trabajador
 */
fun TrabajadorDto.toTrabajador(): Trabajador {
    return Trabajador(
        id = id,
        nombre = nombre,
        telefono = telefono,
        email = email,
        nombreUsuario = nombreUsuario,
        password = password,
        especialidad = especialidad,
        isResponsable = isResponsable,
        salario = salario.toDouble(),
        fechaContratacion = LocalDate.parse(fechaContratacion)
    )
}

/**
 * Funcion para convertir un TrabajadorCreateDto a un Trabajador
 * @return Trabajador
 */
fun TrabajadorCreateDto.toTrabajador(): Trabajador {
    return Trabajador(
        nombre = nombre,
        telefono = telefono,
        email = email,
        nombreUsuario = nombreUsuario,
        password = password,
        especialidad = especialidad,
        isResponsable = isResponsable,
        salario = salario.toDouble(),
        fechaContratacion = LocalDate.parse(fechaContratacion)
    )
}