package repository.propietarioRepository

import config.AppConfig
import db.DatabaseManager
import models.Propietario
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PropietarioRepositoryImplTest {

    private val propietarioRepository: PropietarioRepositoryImpl = PropietarioRepositoryImpl()

    private val propietario1 = Propietario(1, "12345678A", "Pepe", "Perez", "832464754")
    private val propietario2 = Propietario(1, "12345678B", "Juan", "Perez", "832464754")

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByDni() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findByDni("12345678A")
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun findByTelefono() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findByTelefono("832464754")
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun findAll() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val encontrados = propietarioRepository.findAll()
        val propietarioEncontrado = encontrados.find { it.id == 1 }
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun findById() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findById(1)
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun save() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findById(1)
        assertEquals(propietario1, propietarioEncontrado)
    }

    @Test
    fun update() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findById(1)
        propietarioRepository.update(propietario2)
        val propietarioEncontrado2 = propietarioRepository.findById(2)
        assertNotEquals(propietarioEncontrado, propietarioEncontrado2)
    }

    @Test
    fun delete() {
        propietarioRepository.findAll()
        propietarioRepository.save(propietario1)
        val propietarioEncontrado = propietarioRepository.findById(1)
        propietarioRepository.delete(propietario1)
        val propietarioEncontrado2 = propietarioRepository.findById(1)
        assertNotEquals(propietarioEncontrado, propietarioEncontrado2)
    }
}