package es.sergiop.reintentoextra.services.personaService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Service

@Service
class PersonaService
@Autowired constructor(
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
)