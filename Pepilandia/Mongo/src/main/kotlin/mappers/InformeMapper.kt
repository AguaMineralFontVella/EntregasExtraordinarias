package mappers

import dto.InformeCreateDto
import models.Informe

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir un Informe a un InformeDto
 * @return InformeDto
 */
fun InformeCreateDto.toInforme(): Informe {
    return Informe(
        frenado = frenado.toDouble(),
        frenadoIsApto = frenadoIsApto.toBoolean(),
        contaminacion = contaminacion.toDouble(),
        lucesIsApto = lucesIsApto.toBoolean(),
        idCita = idCita,
        idTrabajador = idTrabajador,
        idVehiculo = idVehiculo,
        dniPropietario = dniPropietario,
        isFavorable = isFavorable.toInt()
    )
}
