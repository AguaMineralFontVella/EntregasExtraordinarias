package parser

import models.Trabajador
import models.TrabajadorDTO

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Función que convierte un objeto Trabajador en un objeto TrabajadorDTO
 * @return Objeto TrabajadorDTO
 */
fun Trabajador.toTrabajadorDTO(): TrabajadorDTO{
    return TrabajadorDTO(
        id = this.id,
        nombre = this.nombre,
        telefono = this.telefono,
        email = this.email,
        nombreUsuario = this.nombreUsuario,
        password = this.password,
        especialidad = this.especialidad,
        isResponsable = if(this.isResponsable) 1 else 0,
        salario = this.salario,
        fechaContratacion = this.fechaContratacion.toString(),
    )
}