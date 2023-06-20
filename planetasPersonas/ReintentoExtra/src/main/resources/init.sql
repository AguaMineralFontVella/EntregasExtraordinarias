--Init.sql
--Tabla planetas
CREATE TABLE IF NOT EXISTS PLANETAS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    distancia_tierra BIGINT,
    fecha_viaje DATE,
    personas TEXT
);
