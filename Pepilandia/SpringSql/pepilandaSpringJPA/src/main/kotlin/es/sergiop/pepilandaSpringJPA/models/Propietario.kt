package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("propietarios")
data class Propietario(
    @Id
    val id: Long? = null,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)