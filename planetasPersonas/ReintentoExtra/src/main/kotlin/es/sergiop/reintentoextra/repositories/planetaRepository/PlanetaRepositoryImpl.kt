package es.sergiop.reintentoextra.repositories.planetaRepository

import es.sergiop.reintentoextra.db.DataBaseManager
import es.sergiop.reintentoextra.models.Planeta
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.time.LocalDate

class PlanetaRepositoryImpl: PlanetaRepository {

    override fun findAll(): Flow<Planeta> {
        DataBaseManager.open()
        val planetas = mutableListOf<Planeta>()
        DataBaseManager.use{ db ->
            val result = db.select("SELECT * FROM planetas")
            result?.let {
                while (result.next()) {
                    planetas.add(
                        Planeta(
                            id = it.getInt("id"),
                            nombre = it.getString("nombre"),
                            distanciaTierra = it.getInt("distancia_tierra"),
                            fechaViaje = LocalDate.parse(it.getObject("fecha_viaje").toString()),
                            personas = it.getString("personas")
                        )
                    )
                }
            }

        }
        return planetas.asFlow()
    }

    override fun findById(id: Int): Planeta? {
        DataBaseManager.open()
        var planeta: Planeta? = null
        DataBaseManager.use{ db ->
            val result = db.select("SELECT * FROM planetas WHERE id = $id")
            result?.let {
                while (result.next()) {
                    planeta =  Planeta(
                        id = it.getInt("id"),
                        nombre = it.getString("nombre"),
                        distanciaTierra = it.getInt("distancia_tierra"),
                        fechaViaje = LocalDate.parse(it.getObject("fecha_viaje").toString()),
                        personas = it.getString("personas")
                    )
                }
            }
        }
        return planeta
    }

    override fun save(entity: Planeta): Planeta {
        DataBaseManager.open()
        var planeta: Planeta? = null
        DataBaseManager.use{ db ->
            val sql = "INSERT INTO planetas(nombre, distancia_tierra, fecha_viaje, personas) VALUES (?, ?, ?, ?)"
            val result = db.update(sql, entity.nombre, entity.distanciaTierra, entity.fechaViaje, entity.personas)
            if (result != null) {
                planeta = entity
            }
        }
        return planeta!!
    }

    private fun update(entity: Planeta): Planeta {
        DataBaseManager.open()
        var planeta: Planeta? = null
        DataBaseManager.use{ db ->
            val sql = "UPDATE planetas SET nombre = ?, distancia_tierra = ?, fecha_viaje = ? WHERE id = ?"
            val result = db.update(sql, entity.nombre, entity.distanciaTierra, entity.fechaViaje, entity.id)
            if (result != null) {
                planeta = planeta
            }
        }
        return planeta!!
    }

    override fun delete(entity: Planeta): Boolean {
        DataBaseManager.open()
        var deleted = false
        if (entity.personas == null) {
            DataBaseManager.use{ db ->
                val sql = "DELETE FROM planetas WHERE id = ?"
                val result = db.update(sql, entity.id)
                if (result != null) {
                    deleted = true
                }
            }
        }
        return deleted
    }

}