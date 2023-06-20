package es.sergiop.reintentoextra.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDate

@Document("personas")
data class Persona(
    @Id
    val id: ObjectId = ObjectId.get(),
    val nombre: String,
    val raza: Raza,
    val fechaEncuentro: LocalDate,
    val planeta: String
) : Serializable {
    enum class Raza {
        TERRICOLA, SAIYAN, NAMEKIANO, DIOS, OTRO
    }
}