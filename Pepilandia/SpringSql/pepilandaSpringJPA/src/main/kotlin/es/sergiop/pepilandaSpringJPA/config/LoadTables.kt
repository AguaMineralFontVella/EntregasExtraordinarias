package es.sergiop.pepilandaSpringJPA.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class LoadTables {

    @Bean
    fun initializer(@Qualifier("connectionFactory") connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        if (connectionFactory != null) {
            initializer.setConnectionFactory(connectionFactory)
        }
        val resource = ResourceDatabasePopulator(ClassPathResource("init.sql"))
        initializer.setDatabasePopulator(resource)
        return initializer
    }
}