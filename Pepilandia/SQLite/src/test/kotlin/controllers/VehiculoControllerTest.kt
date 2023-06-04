package controllers

import config.AppConfig
import db.DatabaseManager
import models.Vehiculo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repository.vehiculoRepository.VehiculoRepositoryImpl
import java.time.LocalDate

class VehiculoControllerTest {

    private val vehiculoController = VehiculoController(VehiculoRepositoryImpl())

    private val vehiculo1 = Vehiculo(1, "Seat", "Ibiza", "2346JUT", LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), "12345678A")
    private val vehiculo2 = Vehiculo(1, "Seat", "Ibiza", "MAR223", LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), "12345678B")

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByModelo() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findByModelo("Ibiza").component1()
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findByMatricula() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findByMatricula("2346JUT").component1()
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findByDniPropietario() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val dniPropietario = vehiculo1.dniPropietario
        val vehiculoEncontrado = vehiculoController.findByDniPropietario(dniPropietario).component1()
        assertEquals(vehiculo1, vehiculoEncontrado?.get(0))
    }

    @Test
    fun findAll() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val encontrados = vehiculoController.findAll().component1()
        val vehiculoEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findById() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findById(1).component1()
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun save() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findById(1).component1()
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun update() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findById(1).component1()
        vehiculoController.update(vehiculo2)
        val vehiculoEncontrado2 = vehiculoController.findById(2).component1()
        assertNotEquals(vehiculoEncontrado, vehiculoEncontrado2)
    }

    @Test
    fun delete() {
        vehiculoController.findAll().component1()
        vehiculoController.save(vehiculo1)
        val vehiculoEncontrado = vehiculoController.findById(1).component1()
        vehiculoController.delete(vehiculo1)
        val vehiculoEncontrado2 = vehiculoController.findById(1).component1()
        assertNotEquals(vehiculoEncontrado, vehiculoEncontrado2)
    }
}