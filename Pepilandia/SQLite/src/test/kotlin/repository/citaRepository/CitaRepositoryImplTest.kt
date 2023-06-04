package repository.citaRepository

import config.AppConfig
import db.DatabaseManager
import models.Cita
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CitaRepositoryImplTest {

    private val citaRepository:  CitaRepositoryImpl =  CitaRepositoryImpl()

    private val cita1 = Cita(
        id = 1,
        idTrabajador = 1,
        idVehiculo = 1,
        fecha = LocalDate.parse("2021-05-05"),
        hora = "10:00",
    )

    private val cita2 = Cita(
        id = 2,
        idTrabajador = 2,
        idVehiculo = 2,
        fecha = LocalDate.parse("2021-05-05"),
        hora = "10:00",
    )

    private val cita3 = Cita(
        id = 1,
        idTrabajador = 2,
        idVehiculo = 2,
        fecha = LocalDate.parse("2021-05-05"),
        hora = "10:00",
    )

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findAll() {
        citaRepository.findAll()
        citaRepository.save(cita1)
        citaRepository.save(cita2)
        val citas = citaRepository.findAll()
        assertEquals(2, citas.size)
        assertEquals(cita1, citas[0])
        assertEquals(cita2, citas[1])
    }

    @Test
    fun findById() {
        citaRepository.findAll()
        citaRepository.save(cita1)
        val cita = citaRepository.findById(1)
        assertEquals(cita1, cita)
    }

    @Test
    fun save() {
        citaRepository.findAll()
        citaRepository.save(cita1)
        val citas = citaRepository.findAll()
        assertEquals(1, citas.size)
        assertEquals(cita1, citas[0])
    }

    @Test
    fun update() {
        citaRepository.findAll()
        citaRepository.save(cita1)
        citaRepository.update(cita3)
        val citas = citaRepository.findAll()
        assertEquals(1, citas.size)
        assertEquals(cita3, citas[0])
    }

    @Test
    fun delete() {
        citaRepository.findAll()
        citaRepository.save(cita1)
        citaRepository.save(cita2)
        citaRepository.delete(cita1)
        val citas = citaRepository.findAll()
        assertEquals(1, citas.size)
        assertEquals(cita2, citas[0])
    }
}