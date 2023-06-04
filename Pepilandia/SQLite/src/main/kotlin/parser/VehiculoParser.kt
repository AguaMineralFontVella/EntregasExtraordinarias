package parser

import models.Vehiculo
import models.VehiculoDTO

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Función que convierte un objeto Vehiculo en un objeto VehiculoDTO
 * @return Objeto VehiculoDTO
 */
fun Vehiculo.toVehiculoDTO(): VehiculoDTO {
    return VehiculoDTO(
        id = this.id,
        marca = this.marca,
        modelo = this.modelo,
        matricula = this.matricula,
        fechaMatriculacion = this.fechaMatriculacion.toString(),
        fechaUltimaRevision = this.fechaUltimaRevision.toString(),
        dniPropietario = this.dniPropietario
    )
}