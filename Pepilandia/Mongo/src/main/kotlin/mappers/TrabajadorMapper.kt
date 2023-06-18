package mappers

import dto.TrabajadorCreateDto
import models.Trabajador
import security.BCrypt.cifrar
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

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
        password = cifrar(password),
        especialidad = especialidad,
        isResponsable = isResponsable,
        salario = salario.toDouble(),
        fechaContratacion = LocalDate.parse(fechaContratacion)
    )
}