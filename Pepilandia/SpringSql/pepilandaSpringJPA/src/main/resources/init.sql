--init-sql--

DROP TABLE IF EXISTS Trabajadores;
DROP TABLE IF EXISTS Informes;
DROP TABLE IF EXISTS Especialidades;
DROP TABLE IF EXISTS Propietarios;
DROP TABLE IF EXISTS Vehiculos;
DROP TABLE IF EXISTS Citas;

-- Tabla Especialidad
CREATE TABLE IF NOT EXISTS Especialidades (
    nombre VARCHAR(20) PRIMARY KEY,
    salario_base DECIMAL(10, 2)
);

-- Insertar datos en la tabla Especialidad
INSERT INTO Especialidades (nombre, salario_base) VALUES
    ('ELECTRICIDAD', 1800),
    ('MOTOR', 1700),
    ('MECANICA', 1600),
    ('INTERIOR', 1750);

-- Tabla Propietario
CREATE TABLE Propietarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dni TEXT NOT NULL,
    nombre TEXT NOT NULL,
    apellidos TEXT NOT NULL,
    telefono TEXT NOT NULL
);

-- Tabla Vehiculo
CREATE TABLE IF NOT EXISTS Vehiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca TEXT NOT NULL,
    modelo TEXT NOT NULL,
    matricula TEXT NOT NULL,
    fecha_matriculacion DATE,
    fecha_ultima_revision DATE,
    dni_propietario TEXT NOT NULL
);

-- Tabla Trabajador
CREATE TABLE IF NOT EXISTS Trabajadores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre TEXT NOT NULL,
    telefono TEXT NOT NULL,
    email TEXT NOT NULL,
    NOMBRE_USUARIO TEXT NOT NULL,
    password TEXT NOT NULL,
    especialidad TEXT NOT NULL,
    is_Responsable BOOLEAN NOT NULL,
    salario DOUBLE,
    fecha_contratacion DATE
);

-- Tabla Cita
CREATE TABLE IF NOT EXISTS Citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_trabajador BIGINT,
    id_vehiculo BIGINT,
    fecha DATETIME,
    hora TEXT NOT NULL
);

-- Tabla Informe
CREATE TABLE IF NOT EXISTS Informes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    frenado DOUBLE,
    frenado_Is_Apto TEXT NOT NULL,
    contaminacion DOUBLE,
    luces_Is_Apto TEXT NOT NULL,
    id_cita BIGINT,
    id_trabajador BIGINT,
    id_vehiculo BIGINT,
    dni_propietario TEXT NOT NULL,
    is_Favorable BIGINT
);

INSERT INTO Trabajadores (id, nombre, telefono, email, NOMBRE_USUARIO, password, especialidad, is_Responsable, salario, fecha_contratacion) VALUES
    (1, 'Mark Johnson', '789012345', 'mark@gmail.com', 'JhonMrk002', 'password123', 'MOTOR', false, 00.00, '2020-12-20'),
    (2, 'Sarah Thompson', '012345678', 'sarah@gmail.com', 'ThomRah22', 'password456', 'ELECTRICIDAD', true, 00.00, '2018-05-10'),
    (3, 'Robert Davis', '987654321', 'robert@gmail.com', 'Robertico', 'password789', 'MECANICA', false, 00.00, '2019-09-15'),
    (4, 'Jennifer Wilson', '456789012', 'jennifer@gmail.com', 'Jenni33', 'passwordabc', 'INTERIOR', false, 00.00, '2021-03-20'),
    (5, 'Daniel Smith', '654321987', 'daniel@gmail.com', 'Danielsmith48', 'passwordxyz', 'MOTOR', false, 00.00, '2017-05-21');

INSERT INTO Vehiculos (id, marca, modelo, matricula, fecha_matriculacion, fecha_ultima_revision, dni_propietario) VALUES
    (1, 'Toyota', 'Corolla', '1327DFV', '2020-01-01', '2022-02-15', '12345678P'),
    (2, 'Honda', 'Civic', '8754HPT', '2018-05-10', '2021-08-20', '12345678P'),
    (3, 'Ford', 'Mustang', '0138DJT', '2019-09-15', '2022-01-05', '12345678P'),
    (4, 'Chevrolet', 'Cruze', '5482DDM', '2021-03-20', '2023-04-10', '45678901J'),
    (5, 'Volkswagen', 'Golf', '4535HQY', '2017-11-30', '2023-01-20', '45678901J'),
    (6, 'BMW', 'Serie 3', '7521KRW', '2022-02-28', '2023-05-15', '45678901J'),
    (7, 'Mercedes-Benz', 'Clase C', '6894PLN', '2020-07-05', '2023-02-01', '65498732R'),
    (8, 'Audi', 'A4', '3256RBS', '2019-10-12', '2022-09-10', '65498732R'),
    (9, 'Hyundai', 'Elantra', '9876LMQ', '2018-04-18', '2021-07-25', '65498732R'),
    (10, 'Kia', 'Sportage', '1126GTY', '2021-06-10', '2023-03-05', '98712345Z'),
    (11, 'Nissan', 'Sentra', '4455BHT', '2017-09-25', '2022-12-20', '98712345Z'),
    (12, 'Mazda', 'CX-5', '7854NVF', '2020-03-15', '2023-04-30', '98712345Z'),
    (13, 'Subaru', 'Impreza', '9654KPL', '2019-08-10', '2022-11-05', '78932165K'),
    (14, 'Renault', 'Clio', '5362RKP', '2018-01-20', '2021-06-15', '78932165K'),
    (15, 'Fiat', '500', '2547VHB', '2022-05-05', '2023-03-20', '78932165K');

INSERT INTO Propietarios (id, dni, nombre, apellidos, telefono) VALUES
    (1, '12345678P', 'John', 'Doe', '123456789'),
    (2, '45678901J', 'Jane', 'Smith', '987654321'),
    (3, '65498732R', 'Michael', 'Johnson', '654321987'),
    (4, '98712345Z', 'Emily', 'Davis', '321654987'),
    (5, '78932165K', 'David', 'Wilson', '789123456');