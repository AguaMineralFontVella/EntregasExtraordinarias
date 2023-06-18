package es.sergiop.pepilandaSpringJPA

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

@SpringBootApplication
@EnableCaching
@EnableR2dbcRepositories
class PepilandaSpringJpaApplication {

	@PostConstruct
	fun init() {
		//La función se ejecuta al inicio del programa después de que se inicie Spring

	}
}

fun main(args: Array<String>) {
	runApplication<PepilandaSpringJpaApplication>(*args)
}