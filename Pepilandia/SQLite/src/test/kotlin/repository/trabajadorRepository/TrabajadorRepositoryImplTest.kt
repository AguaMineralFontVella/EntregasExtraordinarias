package repository.trabajadorRepository

import config.AppConfig
import db.DatabaseManager
import models.Trabajador
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

class TrabajadorRepositoryImplTest {

    private val trabajadorRepository: TrabajadorRepositoryImpl = TrabajadorRepositoryImpl()

    //DA ERROR PORQUE LA CONTRASEÑA SE CIFRA AUTOMÁTICAMENTE

    private val trabajador1 = Trabajador(1, "Pepe", "667483324", "TRABAJADOR1@gmail.com", "Pepe667", "[B@1ce61929", "ELECTRICIDAD", true, 2800.0, LocalDate.parse("2021-01-01"))
    private val trabajador2 = Trabajador(1, "Juan", "645279424", "TRABAJADOR2@gmail.com", "Juan645", "gnyrf", "ELECTRICIDAD", true, 0.00, LocalDate.parse("2021-01-01"))

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByTelefono() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findAll().component1()
        assertEquals(trabajador1, encontrado)
    }

    @Test
    fun findByEmail() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findByEmail("TRABAJADOR1@gmail.com")
        assertEquals(trabajador1, encontrado)
    }

    @Test
    fun findByNombreUsuario() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findByNombreUsuario("Pepe667")
        assertEquals(trabajador1, encontrado)
    }

    @Test
    fun findByEspecialidad() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findByEspecialidad("ELECTRICIDAD")
        assertEquals(trabajador1, encontrado[0])
    }

    @Test
    fun findByIsResponsable() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findByIsResponsable(true)
        assertEquals(trabajador1, encontrado[0])
    }

    @Test
    fun findAll() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        trabajadorRepository.save(trabajador2)
        val encontrado = trabajadorRepository.findAll()
        assertEquals(trabajador1, encontrado.component1())
        assertEquals(trabajador2, encontrado.component2())
    }

    @Test
    fun findById() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findById(1)
        assertEquals(trabajador1, encontrado)
    }

    @Test
    fun save() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        val encontrado = trabajadorRepository.findAll().component1()
        assertEquals(trabajador1, encontrado)
    }

    @Test
    fun update() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        trabajadorRepository.update(trabajador2)
        val encontrado = trabajadorRepository.findAll().component1()
        assertEquals(trabajador2, encontrado)
    }

    @Test
    fun delete() {
        trabajadorRepository.findAll()
        trabajadorRepository.save(trabajador1)
        trabajadorRepository.delete(trabajador1)
        val encontrado = trabajadorRepository.findAll()
        assertEquals(0, encontrado.size)
    }
}