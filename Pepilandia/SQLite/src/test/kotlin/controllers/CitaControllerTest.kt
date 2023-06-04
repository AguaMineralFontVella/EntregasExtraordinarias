package controllers

import config.AppConfig
import db.DatabaseManager
import models.Cita
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repository.citaRepository.CitaRepositoryImpl
import java.text.SimpleDateFormat
import java.time.ZoneId

class CitaControllerTest {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val citaController = CitaController(CitaRepositoryImpl())
    private val cita1 = Cita(1, 1,1, dateFormat.parse(("2021-05-05").toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "10:00")
    private val cita2= Cita(1, 2,2, dateFormat.parse(("2022-05-05").toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "10:00")

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findAll() {
        citaController.findAll().component1()
        citaController.save(cita1)
        val encontrados = citaController.findAll().component1()
        val citaEncontrada = encontrados?.find { it.id == 1 }
        assertEquals(cita1, citaEncontrada)
    }

    @Test
    fun findById() {
        citaController.findAll().component1()
        citaController.save(cita1)
        val citaEncontrada = citaController.findById(1).component1()
        assertEquals(cita1, citaEncontrada)
    }

    @Test
    fun save() {
        citaController.findAll().component1()
        citaController.save(cita1)
        val citaEncontrada = citaController.findById(1).component1()
        assertEquals(cita1, citaEncontrada)
    }

    @Test
    fun update() {
        citaController.findAll().component1()
        citaController.save(cita1)
        val citaEncontrada = citaController.findById(1).component1()
        citaController.update(cita2)
        val citaEncontrada2 = citaController.findById(1).component1()
        assertNotEquals(citaEncontrada, citaEncontrada2)

    }

    @Test
    fun delete() {
        citaController.findAll().component1()
        citaController.save(cita1)
        val citaEncontrada = citaController.findById(1).component1()
        citaController.delete(cita1)
        val citaEncontrada2 = citaController.findById(1).component1()
        assertNotEquals(citaEncontrada, citaEncontrada2)
    }
}