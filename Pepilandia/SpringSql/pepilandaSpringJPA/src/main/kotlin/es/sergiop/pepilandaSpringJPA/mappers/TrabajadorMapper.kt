package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.TrabajadorCreateDto
import es.sergiop.pepilandaSpringJPA.dto.TrabajadorDto
import es.sergiop.pepilandaSpringJPA.models.Trabajador
import es.sergiop.pepilandaSpringJPA.security.BCrypt.cifrar
import java.time.LocalDate

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