package repository.vehiculoRepository

import config.AppConfig
import db.DatabaseManager
import models.Vehiculo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

class VehiculoRepositoryImplTest {

    private val vehiculoRepository: VehiculoRepositoryImpl = VehiculoRepositoryImpl()

    private val vehiculo1 = Vehiculo(1, "Seat", "Ibiza", "2346JUT", LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), "12345678A")
    private val vehiculo2 = Vehiculo(1, "Seat", "Ibiza", "MAR223", LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01"), "12345678B")

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByModelo() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findByModelo("Ibiza")
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findByMatricula() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findByMatricula("2346JUT")
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findByDniPropietario() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val dniPropietario = vehiculo1.dniPropietario
        val vehiculoEncontrado = vehiculoRepository.findByDniPropietario(dniPropietario)
        assertEquals(vehiculo1, vehiculoEncontrado[0])
    }

    @Test
    fun findAll() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val encontrados = vehiculoRepository.findAll()
        val vehiculoEncontrado = encontrados.find { it.id == 1 }
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun findById() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findById(1)
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun save() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findById(1)
        assertEquals(vehiculo1, vehiculoEncontrado)
    }

    @Test
    fun update() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findById(1)
        assertEquals(vehiculo1, vehiculoEncontrado)
        vehiculoRepository.update(vehiculo2)
        val vehiculoEncontrado2 = vehiculoRepository.findById(1)
        assertEquals(vehiculo2, vehiculoEncontrado2)
    }

    @Test
    fun delete() {
        vehiculoRepository.findAll()
        vehiculoRepository.save(vehiculo1)
        val vehiculoEncontrado = vehiculoRepository.findById(1)
        assertEquals(vehiculo1, vehiculoEncontrado)
        vehiculoRepository.delete(vehiculo1)
        val vehiculoEncontrado2 = vehiculoRepository.findById(1)
        assertEquals(null, vehiculoEncontrado2)
    }
}