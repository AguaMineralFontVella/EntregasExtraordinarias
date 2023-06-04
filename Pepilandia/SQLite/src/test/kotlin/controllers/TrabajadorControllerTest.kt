package controllers

import config.AppConfig
import db.DatabaseManager
import models.Trabajador
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import repository.trabajadorRepository.TrabajadorRepositoryImpl
import java.time.LocalDate

class TrabajadorControllerTest {

    private val trabajadorController = TrabajadorController(TrabajadorRepositoryImpl())

    //DA ERROR PORQUE LA CONTRASEÑA SE CIFRA AUTOMÁTICAMENTE

    private val trabajador1 = Trabajador(1, "Pepe", "667483324", "TRABAJADOR1@gmail.com", "Pepe667", "[B@3a94964", "ELECTRICIDAD", true, 2800.0, LocalDate.parse("2021-01-01"))
    private val trabajador2 = Trabajador(1, "Juan", "645279424", "TRABAJADOR2@gmail.com", "Juan645", "gnyrf", "ELECTRICIDAD", true, 0.00, LocalDate.parse("2021-01-01"))

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByTelefono() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.telefono == "667483324" }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findByEmail() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.email == "TRABAJADOR1@gmail.com" }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findByNombreUsuario() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.nombreUsuario == "Pepe667" }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findByEspecialidad() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.especialidad == "ELECTRICIDAD" }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findByIsResponsable() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.isResponsable }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findAll() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun findById() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun save() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(trabajador1, trabajadorEncontrado)
    }

    @Test
    fun update() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(trabajador1, trabajadorEncontrado)
        trabajadorController.update(trabajador2)
        val encontrados2 = trabajadorController.findAll().component1()
        val trabajadorEncontrado2 = encontrados2?.find { it.id == 1 }
        assertEquals(trabajador2, trabajadorEncontrado2)
    }

    @Test
    fun delete() {
        trabajadorController.findAll().component1()
        trabajadorController.save(trabajador1)
        val encontrados = trabajadorController.findAll().component1()
        val trabajadorEncontrado = encontrados?.find { it.id == 1 }
        assertEquals(trabajador1, trabajadorEncontrado)
        trabajadorController.delete(trabajador1)
        val encontrados2 = trabajadorController.findAll().component1()
        val trabajadorEncontrado2 = encontrados2?.find { it.id == 1 }
        assertEquals(null, trabajadorEncontrado2)
    }
}