package es.sergiop.reintentoextra.db

import es.sergiop.reintentoextra.models.Persona
import es.sergiop.reintentoextra.models.Planeta
import org.bson.types.ObjectId
import java.time.LocalDate

fun getPlanetasInit() = listOf<Planeta>(
    Planeta(
        id = 1,
        nombre = "Tierra",
        distanciaTierra = 0,
        fechaViaje = LocalDate.parse("2021-01-01"),
        personas = (mapOf(
            1 to goku,
            2 to vegeta,
            3 to krillin,
            4 to gohan,
        )).toString()
    ),

    Planeta(
        id = 2,
        nombre = "Namek",
        distanciaTierra = 823426433,
        fechaViaje = LocalDate.parse("2021-01-01"),
        personas = (mapOf(
           1 to Persona(nombre = "Piccolo", raza = Persona.Raza.NAMEKIANO, fechaEncuentro = LocalDate.parse("2021-01-01"), planeta = "namek")
        )).toString()
    )
)

val goku = Persona(
    id = ObjectId.get(),
    nombre = "Goku",
    raza = Persona.Raza.SAIYAN,
    fechaEncuentro = LocalDate.parse("2021-01-01"),
    planeta = "tierra"
)

val vegeta = Persona(
    id = ObjectId.get(),
    nombre = "Vegeta",
    raza = Persona.Raza.SAIYAN,
    fechaEncuentro = LocalDate.parse("2021-01-01"),
    planeta = "tierra"
)

val piccolo = Persona(
    id = ObjectId.get(),
    nombre = "Piccolo",
    raza = Persona.Raza.NAMEKIANO,
    fechaEncuentro = LocalDate.parse("2021-01-01"),
    planeta = "namek"
)

val krillin = Persona(
    id = ObjectId.get(),
    nombre = "Krillin",
    raza = Persona.Raza.TERRICOLA,
    fechaEncuentro = LocalDate.parse("2021-01-01"),
    planeta = "tierra"
)

val gohan = Persona(
    id = ObjectId.get(),
    nombre = "Gohan",
    raza = Persona.Raza.SAIYAN,
    fechaEncuentro = LocalDate.parse("2021-01-01"),
    planeta = "tierra"
)

fun getPersonasInit() = listOf<Persona>(
    goku,
    vegeta,
    piccolo,
    krillin,
    gohan
)