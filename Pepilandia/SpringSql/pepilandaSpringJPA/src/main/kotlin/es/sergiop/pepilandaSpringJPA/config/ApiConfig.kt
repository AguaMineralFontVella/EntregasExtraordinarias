package es.sergiop.pepilandaSpringJPA.config

import org.springframework.context.annotation.Configuration

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Configuración de la API
 */
@Configuration
class ApiConfig {
    companion object {
        const val API_PATH = "/pepilandia"
    }
}