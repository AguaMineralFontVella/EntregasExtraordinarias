package controllers

import config.AppConfig
import db.DatabaseManager
import models.Informe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repository.informeRepository.InformeRepositoryImpl

class InformeControllerTest {

    private val informeController = InformeController(InformeRepositoryImpl())

    private val informe1 = Informe(1, 2.0, true, 33.0, true, 2, 2, 2, "12345678A", 1)
    private val informe2 = Informe(1, 6.0, true, 38.0, true, 2, 2, 2, "12345678A", 1)

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByIsFavorable() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val encontrados = informeController.findAll().component1()
        val informeEncontrado = encontrados?.find { it.isFavorable == 1 }
        assertEquals(informe1, informeEncontrado)
    }

    @Test
    fun findAll() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val encontrados = informeController.findAll().component1()
        val informeEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(informe1, informeEncontrado)
    }

    @Test
    fun findById() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val informeEncontrado = informeController.findById(1).component1()
        assertEquals(informe1, informeEncontrado)
    }

    @Test
    fun save() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val informeEncontrado = informeController.findById(1).component1()
        assertEquals(informe1, informeEncontrado)
    }

    @Test
    fun update() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val informeEncontrado = informeController.findById(1).component1()
        informeController.update(informe2)
        val informeEncontrado2 = informeController.findById(1).component1()
        assertNotEquals(informeEncontrado, informeEncontrado2)
    }

    @Test
    fun delete() {
        informeController.findAll().component1()
        informeController.save(informe1)
        val informeEncontrado = informeController.findById(1).component1()
        informeController.delete(informe1)
        val informeEncontrado2 = informeController.findById(1).component1()
        assertNotEquals(informeEncontrado, informeEncontrado2)
    }
}