package repository.informeRepository

import config.AppConfig
import db.DatabaseManager
import models.Informe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class InformeRepositoryImplTest {

    private val informeRepository: InformeRepositoryImpl =  InformeRepositoryImpl()

    private val informe1 = Informe(1, 2.0, true, 33.0, true, 2, 2, 2, "12345678A", 1)
    private val informe2 = Informe(1, 6.0, true, 38.0, true, 2, 2, 2, "12345678A", 1)

    init{
        AppConfig.initConfig()
        DatabaseManager.initData()
    }

    @Test
    fun findByIsFavorable() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        val informes = informeRepository.findByIsFavorable()
        assertEquals(informe1, informes[0])
    }

    @Test
    fun findAll() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        informeRepository.save(informe2)
        val informes = informeRepository.findAll()
        assertEquals(2, informes.size)
    }

    @Test
    fun findById() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        val informe = informeRepository.findById(1)
        assertEquals(informe1, informe)
    }

    @Test
    fun save() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        val informe = informeRepository.findById(1)
        assertEquals(informe1, informe)
    }

    @Test
    fun update() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        informeRepository.update(informe2)
        val informe = informeRepository.findById(1)
        assertEquals(informe2, informe)
    }

    @Test
    fun delete() {
        informeRepository.findAll()
        informeRepository.save(informe1)
        informeRepository.delete(informe1)
        val informe = informeRepository.findById(1)
        assertEquals(null, informe)
    }
}