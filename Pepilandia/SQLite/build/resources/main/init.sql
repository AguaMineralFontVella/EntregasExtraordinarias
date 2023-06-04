--init-sql--

DROP TABLE IF EXISTS Trabajador;
DROP TABLE IF EXISTS Informe;
DROP TABLE IF EXISTS Especialidad;
DROP TABLE IF EXISTS Propietario;
DROP TABLE IF EXISTS Vehiculo;
DROP TABLE IF EXISTS Cita;

-- Tabla Especialidad
CREATE TABLE IF NOT EXISTS Especialidad (
    nombre VARCHAR(20) PRIMARY KEY,
    salario_base DECIMAL(10, 2)
);

-- Insertar datos en la tabla Especialidad
INSERT INTO Especialidad (nombre, salario_base) VALUES
    ('ELECTRICIDAD', 1800),
    ('MOTOR', 1700),
    ('MECANICA', 1600),
    ('INTERIOR', 1750);

-- Tabla Propietario
CREATE TABLE Propietario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dni VARCHAR(9),
    nombre VARCHAR(30),
    apellidos VARCHAR(100),
    telefono VARCHAR(20)
);

-- Tabla Vehiculo
CREATE TABLE IF NOT EXISTS Vehiculo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    matricula VARCHAR(10),
    fecha_matriculacion DATE,
    fecha_ultima_revision DATE,
    dni_propietario VARCHAR(9),
    FOREIGN KEY (dni_propietario) REFERENCES Propietario(dni)
);

-- Tabla Trabajador
CREATE TABLE IF NOT EXISTS Trabajador (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(30),
    telefono VARCHAR(20),
    email VARCHAR(100),
    nombreUsuario VARCHAR(20),
    password VARCHAR(100),
    especialidad_trabajador VARCHAR(20),
    isResponsable INTEGER,
    salario DECIMAL(10, 2),
    fecha_contratacion DATE,
    FOREIGN KEY (especialidad_trabajador) REFERENCES Especialidad(nombre)
);

-- Tabla Cita
CREATE TABLE IF NOT EXISTS Cita (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_trabajador INTEGER,
    id_vehiculo INTEGER,
    fecha DATETIME,
    hora VARCHAR(5),
    FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id),
    FOREIGN KEY (id_vehiculo) REFERENCES Vehiculo(id)
);

-- Tabla Informe
CREATE TABLE IF NOT EXISTS Informe (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    frenado DECIMAL(2, 2),
    frenadoIsApto INTEGER,
    contaminacion DECIMAL(2, 2),
    lucesIsApto INTEGER,
    id_cita INTEGER,
    id_trabajador INTEGER,
    id_vehiculo INT,
    dni_propietario VARCHAR(9),
    isFavorable INTEGER,
    FOREIGN KEY (id_cita) REFERENCES Cita(id),
    FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id),
    FOREIGN KEY (id_vehiculo) REFERENCES Vehiculo(id),
    FOREIGN KEY (dni_propietario) REFERENCES Propietario(dni)
);