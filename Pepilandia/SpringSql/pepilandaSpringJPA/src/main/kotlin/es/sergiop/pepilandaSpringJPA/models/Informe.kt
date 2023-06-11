package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("informes")
data class Informe(
    @Id
    val id: Long? = null,
    val frenado: Double,
    val frenadoIsApto: Boolean,
    val contaminacion: Double,
    val lucesIsApto: Boolean,
    val idCita: Int,
    val idTrabajador: Int,
    val idVehiculo: Int,
    val dniPropietario: String,
    val isFavorable: Int // 0: No, 1: Si
)