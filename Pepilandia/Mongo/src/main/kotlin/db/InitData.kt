package db

import dto.PropietarioCreateDto
import dto.TrabajadorCreateDto
import dto.VehiculoCreateDto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Función para obtener los propietarios iniciales de la base de datos
 * @return Lista de propietarios
 */
fun getPropietariosInit(): List<PropietarioCreateDto> = listOf(
    PropietarioCreateDto(
        dni = "12345678P",
        nombre = "John",
        apellidos = "Doe",
        telefono = "123456789"
    ),
    PropietarioCreateDto(
        dni = "45678901J",
        nombre = "Jane",
        apellidos = "Smith",
        telefono = "987654321"
    ),
    PropietarioCreateDto(
        dni = "65498732R",
        nombre = "Michael",
        apellidos = "Johnson",
        telefono = "654321987"
    ),
    PropietarioCreateDto(
        dni = "98712345Z",
        nombre = "Emily",
        apellidos = "Davis",
        telefono = "321654987"
    ),
    PropietarioCreateDto(
        dni = "78932165K",
        nombre = "David",
        apellidos = "Wilson",
        telefono = "789123456"
    )
)

/**
 * Función para obtener los vehículos iniciales de la base de datos
 * @return Lista de vehículos
 */
fun getTrabajadoresInit(): List<TrabajadorCreateDto> = listOf(
    TrabajadorCreateDto(
        nombre = "Mark Johnson",
        telefono = "789012345",
        email = "mark@gmail.com",
        nombreUsuario = "JhonMrk002",
        password = "password123",
        especialidad = "MOTOR",
        isResponsable = false,
        salario = "0.00",
        fechaContratacion = "2020-12-20"
    ),
    TrabajadorCreateDto(
        nombre = "Sarah Thompson",
        telefono = "012345678",
        email = "sarah@gmail.com",
        nombreUsuario = "ThomRah22",
        password = "password456",
        especialidad = "ELECTRICIDAD",
        isResponsable = true,
        salario = "0.00",
        fechaContratacion = "2018-05-10"
    ),
    TrabajadorCreateDto(
        nombre = "Robert Davis",
        telefono = "987654321",
        email = "robert@gmail.com",
        nombreUsuario = "Robertico",
        password = "password789",
        especialidad = "MECANICA",
        isResponsable = false,
        salario = "0.00",
        fechaContratacion = "2019-09-15"
    ),
    TrabajadorCreateDto(
        nombre = "Jennifer Wilson",
        telefono = "456789012",
        email = "jennifer@gmail.com",
        nombreUsuario = "Jenni33",
        password = "passwordabc",
        especialidad = "INTERIOR",
        isResponsable = false,
        salario = "0.00",
        fechaContratacion = "2021-03-20"
    ),
    TrabajadorCreateDto(
        nombre = "Daniel Smith",
        telefono = "654321987",
        email = "daniel@gmail.com",
        nombreUsuario = "Danielsmith48",
        password = "passwordxyz",
        especialidad = "MOTOR",
        isResponsable = false,
        salario = "0.00",
        fechaContratacion = "2017-05-21"
    )
)

/**
 * Función para obtener los vehículos iniciales de la base de datos
 * @return Lista de vehículos
 */
fun getVehiculosInit(): List<VehiculoCreateDto> = listOf(
    VehiculoCreateDto(
        marca = "Toyota",
        modelo = "Corolla",
        matricula = "1327DFV",
        fechaMatriculacion = "2020-01-01",
        fechaUltimaRevision = "2022-02-15",
        dniPropietario = "12345678P"
    ),
    VehiculoCreateDto(
        marca = "Honda",
        modelo = "Civic",
        matricula = "8754HPT",
        fechaMatriculacion = "2018-05-10",
        fechaUltimaRevision = "2021-08-20",
        dniPropietario = "12345678P"
    ),
    VehiculoCreateDto(
        marca = "Ford",
        modelo = "Mustang",
        matricula = "0138DJT",
        fechaMatriculacion = ("2019-09-15"),
        fechaUltimaRevision = ("2022-01-05"),
        dniPropietario = "12345678P"
    ),
    VehiculoCreateDto(
        marca = "Chevrolet",
        modelo = "Cruze",
        matricula = "5482DDM",
        fechaMatriculacion = ("2021-03-20"),
        fechaUltimaRevision = ("2023-04-10"),
        dniPropietario = "45678901J"
    ),
    VehiculoCreateDto(
        marca = "Volkswagen",
        modelo = "Golf",
        matricula = "4535HQY",
        fechaMatriculacion = ("2017-11-30"),
        fechaUltimaRevision = ("2023-01-20"),
        dniPropietario = "45678901J"
    ),
    VehiculoCreateDto(
        marca = "BMW",
        modelo = "Serie 3",
        matricula = "7521KRW",
        fechaMatriculacion = ("2022-02-28"),
        fechaUltimaRevision = ("2023-05-15"),
        dniPropietario = "45678901J"
    ),
    VehiculoCreateDto(
        marca = "Mercedes-Benz",
        modelo = "Clase C",
        matricula = "6894PLN",
        fechaMatriculacion = ("2020-07-05"),
        fechaUltimaRevision = ("2023-02-01"),
        dniPropietario = "65498732R"
    ),
    VehiculoCreateDto(
        marca = "Audi",
        modelo = "A4",
        matricula = "3256RBS",
        fechaMatriculacion = ("2019-10-12"),
        fechaUltimaRevision = ("2022-09-10"),
        dniPropietario = "65498732R"
    ),
    VehiculoCreateDto(
        marca = "Hyundai",
        modelo = "Elantra",
        matricula = "9876LMQ",
        fechaMatriculacion = ("2018-04-18"),
        fechaUltimaRevision = ("2021-07-25"),
        dniPropietario = "65498732R"
    ),
    VehiculoCreateDto(
        marca = "Kia",
        modelo = "Sportage",
        matricula = "1126GTY",
        fechaMatriculacion = ("2021-06-10"),
        fechaUltimaRevision = ("2023-03-05"),
        dniPropietario = "98712345Z"
    ),
    VehiculoCreateDto(
        marca = "Nissan",
        modelo = "Sentra",
        matricula = "4455BHT",
        fechaMatriculacion = ("2017-09-25"),
        fechaUltimaRevision = ("2022-12-20"),
        dniPropietario = "98712345Z"
    ),
    VehiculoCreateDto(
        marca = "Mazda",
        modelo = "CX-5",
        matricula = "7854NVF",
        fechaMatriculacion = ("2020-03-15"),
        fechaUltimaRevision = ("2023-04-30"),
        dniPropietario = "98712345Z"
    ),
    VehiculoCreateDto(
        marca = "Subaru",
        modelo = "Impreza",
        matricula = "9654KPL",
        fechaMatriculacion = ("2019-08-10"),
        fechaUltimaRevision = ("2022-11-05"),
        dniPropietario = "78932165K"
    ),
    VehiculoCreateDto(
        marca = "Renault",
        modelo = "Clio",
        matricula = "5362RKP",
        fechaMatriculacion = "2018-01-20",
        fechaUltimaRevision = "2021-06-15",
        dniPropietario = "78932165K"
    ),
    VehiculoCreateDto(
        marca = "Fiat",
        modelo = "500",
        matricula = "2547VHB",
        fechaMatriculacion = "2022-05-05",
        fechaUltimaRevision = "2023-03-20",
        dniPropietario = "78932165K"
    )
)