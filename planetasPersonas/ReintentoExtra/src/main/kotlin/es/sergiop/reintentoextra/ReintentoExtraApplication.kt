package es.sergiop.reintentoextra

import es.sergiop.reintentoextra.config.AppConfig.initConfig
import es.sergiop.reintentoextra.controller.PersonasController
import es.sergiop.reintentoextra.controller.PlanetasController
import es.sergiop.reintentoextra.db.DataBaseManager.initData
import es.sergiop.reintentoextra.db.getPersonasInit
import es.sergiop.reintentoextra.db.getPlanetasInit
import es.sergiop.reintentoextra.models.Persona
import es.sergiop.reintentoextra.models.Planeta
import es.sergiop.reintentoextra.repositories.planetaRepository.PlanetaRepositoryImpl
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import java.time.LocalDate

@SpringBootApplication
@EnableR2dbcRepositories
class ReintentoExtraApplication
@Autowired constructor(
    private val personasController : PersonasController
) : CommandLineRunner{

    val planetasController = PlanetasController(PlanetaRepositoryImpl())

    override fun run(vararg args: String?) = runBlocking {

        initConfig()
        initData()

        val planetas = mutableListOf<Planeta>()
        val planetasInit = getPlanetasInit()
        val personas = mutableListOf<Persona>()
        val personaInit = getPersonasInit()

        val clear = launch {
            personasController.deleteAllPersonas()
        }
        clear.join()

        val save = launch {
            personaInit.forEach {
                personas.add(personasController.personasSave(it))
            }
            planetasInit.forEach {
                planetas.add(planetasController.planetasSave(it))
            }
        }
        save.join()

        personasController.personasGetAll().toList().forEach {
            println(it)
        }

        planetasController.planetasGetAll().toList().forEach {
            println(it)
        }

        val kaio = Persona(ObjectId.get() ,"Kaio-Sama", Persona.Raza.DIOS, LocalDate.parse("2021-05-05"), "Kaio")
        //añade un planeta
        val planetaKaio = Planeta(3, "Kaio", 5437952, LocalDate.parse("2021-05-05"), kaio.toString())
        planetasController.planetasSave(planetaKaio)

        val planetaYardrat = Planeta(4, "Yardrat", 746345342, LocalDate.parse("2021-05-05"), null)
        planetasController.planetasSave(planetaYardrat)

        //Beerus intenta borrar un planeta pero como Namek tiene habitantes no se borran
        val planeta2 = planetasController.planetasGetById(2)
        planetasController.deletePlaneta(planeta2!!)

        //Beerus intenta borrar un planeta pero como Yardrat no tiene habitantes es eliminado
        val planeta3 = planetasController.planetasGetById(4)
        planetasController.deletePlaneta(planeta3!!)

        planetasController.planetasGetAll().toList().forEach {
            println(it)
        }

        //dado un planeta, listar sus personajes
        val planeta1 = planetasController.planetasGetById(1)
        val personajesPlaneta1 = personasController.personasGetByPlaneta(planeta1!!)
        println("Habitantes del planeta ${planeta1.nombre}:")
        personajesPlaneta1.toList().forEach {
            println(it)
        }

        //Añadir un personaje
        var trunks = Persona(ObjectId.get() ,"Trunks", Persona.Raza.SAIYAN, LocalDate.parse("2021-05-05"), "Tierra")
        personasController.personasSave(trunks)

        //Elimina un personaje porque Krillin explota
        val personajes = personasController.personasGetAll().toList()
        personasController.deletePersona(personajes[3].id)
        personasController.personasGetAll().toList().forEach {
            println(it)
        }

        println("a")

        // dado un personaje, obtener la informacion de su planeta.
        val planetaPersonaje1 = personasController.getInformacionPlaneta(trunks)
        println("Planeta de ${trunks.nombre}: $planetaPersonaje1")
    }

}

fun main(args: Array<String>) {
    runApplication<ReintentoExtraApplication>(*args)
}