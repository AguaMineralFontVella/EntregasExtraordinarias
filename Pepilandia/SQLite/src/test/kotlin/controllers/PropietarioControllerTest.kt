package controllers

import config.AppConfig
import db.DatabaseManager
import models.Propietario
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repository.propietarioRepository.PropietarioRepositoryImpl

class PropietarioControllerTest {

    private val propietarioController = PropietarioController(PropietarioRepositoryImpl())

    private val propietario1 = Propietario(1, "12345678A", "Pepe", "Perez", "832464754")
    private val propietario2 = Propietario(1, "12345678B", "Juan", "Perez", "832464754")

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findAll() {
        propietarioController.findAll().component1()
        propietarioController.save(propietario1)
        val encontrados = propietarioController.findAll().component1()
        val propietarioEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun findById() {
        propietarioController.findAll().component1()
        propietarioController.save(propietario1)
        val propietarioEncontrado = propietarioController.findById(1).component1()
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun save() {
        propietarioController.findAll().component1()
        propietarioController.save(propietario1)
        val propietarioEncontrado = propietarioController.findById(1).component1()
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun update() {
        propietarioController.findAll().component1()
        propietarioController.save(propietario1)
        val propietarioEncontrado = propietarioController.findById(1).component1()
        propietarioController.update(propietario2)
        val propietarioEncontrado2 = propietarioController.findById(2).component1()
        assertNotEquals(propietarioEncontrado, propietarioEncontrado2)
    }

    @Test
    fun delete() {
        propietarioController.findAll().component1()
        propietarioController.save(propietario1)
        val propietarioEncontrado = propietarioController.findById(1).component1()
        propietarioController.delete(propietario1)
        val propietarioEncontrado2 = propietarioController.findById(1).component1()
        assertNotEquals(propietarioEncontrado, propietarioEncontrado2)
    }
}