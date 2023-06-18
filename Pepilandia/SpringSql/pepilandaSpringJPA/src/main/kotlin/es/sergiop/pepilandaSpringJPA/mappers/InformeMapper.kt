package es.sergiop.pepilandaSpringJPA.mappers

import es.sergiop.pepilandaSpringJPA.dto.InformeCreateDto
import es.sergiop.pepilandaSpringJPA.dto.InformeDto
import es.sergiop.pepilandaSpringJPA.models.Informe

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Funcion para convertir un Informe a un InformeDto
 * @return InformeDto
 */
fun Informe.toInformeDto(): InformeDto {
    return InformeDto(
        id= id,
        frenado= frenado.toString(),
        frenadoIsApto= frenadoIsApto.toString(),
        contaminacion= contaminacion.toString(),
        lucesIsApto= lucesIsApto.toString(),
        idCita= idCita.toString(),
        idTrabajador= idTrabajador.toString(),
        idVehiculo= idVehiculo.toString(),
        dniPropietario= dniPropietario,
        isFavorable= isFavorable.toString(),
    )
}

/**
 * Funcion para convertir un InformeDto a un Informe
 * @return Informe
 */
fun InformeDto.toInforme(): Informe{
    return Informe(
        id= id,
        frenado= frenado.toDouble(),
        frenadoIsApto= frenadoIsApto.toBoolean(),
        contaminacion= contaminacion.toDouble(),
        lucesIsApto= lucesIsApto.toBoolean(),
        idCita= idCita.toInt(),
        idTrabajador= idTrabajador.toInt(),
        idVehiculo= idVehiculo.toInt(),
        dniPropietario= dniPropietario,
        isFavorable= isFavorable.toInt(),
    )
}

/**
 * Funcion para convertir un InformeCreateDto a un Informe
 * @return Informe
 */
fun InformeCreateDto.toInforme(): Informe{
    return Informe(
        frenado= frenado.toDouble(),
        frenadoIsApto= frenadoIsApto.toBoolean(),
        contaminacion= contaminacion.toDouble(),
        lucesIsApto= lucesIsApto.toBoolean(),
        idCita= idCita.toInt(),
        idTrabajador= idTrabajador.toInt(),
        idVehiculo= idVehiculo.toInt(),
        dniPropietario= dniPropietario,
        isFavorable= isFavorable.toInt(),
    )
}