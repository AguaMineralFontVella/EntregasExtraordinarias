package repository.trabajadorRepository

import db.DatabaseManager
import models.Trabajador
import mu.KotlinLogging
import security.BCrypt.cifrar
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que implementa la interfaz TrabajadorRepository y que se encarga de gestionar los trabajadores
 * @property findByTelefono Función que devuelve un trabajador según su teléfono
 * @property findByEmail Función que devuelve un trabajador según su email
 * @property findByNombreUsuario Función que devuelve un trabajador según su nombre de usuario
 * @property findByEspecialidad Función que devuelve todos los trabajadores según su especialidad
 * @property findByIsResponsable Función que devuelve todos los trabajadores según si es responsable o no
 * @property findAll Función que devuelve todos los trabajadores
 * @property findById Función que devuelve un trabajador según su id
 * @property save Función que guarda un trabajador
 * @property update Función que actualiza un trabajador
 * @property delete Función que elimina un trabajador
 * @property calcularSalario Función que calcula el salario de un trabajador
 */
class TrabajadorRepositoryImpl : TrabajadorRepository {

    /**
     * Función que devuelve un trabajador según su teléfono
     * @param telefono Teléfono del trabajador
     * @return Trabajador
     */
    override fun findByTelefono(telefono: String): Trabajador? {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE telefono = ?"
            val result = db.select(sql, telefono)
            result?.let{
                while(result.next()){
                    trabajador = Trabajador(
                        id = result.getInt("id"),
                        nombre = result.getString("nombre"),
                        telefono = result.getString("telefono"),
                        email = result.getString("email"),
                        nombreUsuario = result.getString("nombreUsuario"),
                        password = cifrar(result.getString("password")),
                        especialidad = result.getString("especialidad_trabajador"),
                        isResponsable = result.getBoolean("isResponsable"),
                        salario = result.getDouble("salario"),
                        fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                    )
                }
            }
        }
        trabajador?.salario = trabajador?.let { calcularSalario(it) }!!
        return trabajador
    }

    /**
     * Función que devuelve un trabajador según su email
     * @param email Email del trabajador
     * @return Trabajador
     */
    override fun findByEmail(email: String): Trabajador? {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE email = ?"
            val result = db.select(sql, email)
            result?.let{
                while(result.next()){
                    trabajador = Trabajador(
                        id = result.getInt("id"),
                        nombre = result.getString("nombre"),
                        telefono = result.getString("telefono"),
                        email = result.getString("email"),
                        nombreUsuario = result.getString("nombreUsuario"),
                        password = cifrar(result.getString("password")),
                        especialidad = result.getString("especialidad_trabajador"),
                        isResponsable = result.getBoolean("isResponsable"),
                        salario = result.getDouble("salario"),
                        fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                    )
                }
            }
        }
        trabajador?.salario = trabajador?.let { calcularSalario(it) }!!
        return trabajador
    }

    /**
     * Función que devuelve un trabajador según su nombre de usuario
     * @param nombreUsuario Nombre de usuario del trabajador
     * @return Trabajador
     */
    override fun findByNombreUsuario(nombreUsuario: String): Trabajador? {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE nombreUsuario = ?"
            val result = db.select(sql, nombreUsuario)
            result?.let{
                while(result.next()){
                    trabajador = Trabajador(
                        id = result.getInt("id"),
                        nombre = result.getString("nombre"),
                        telefono = result.getString("telefono"),
                        email = result.getString("email"),
                        nombreUsuario = result.getString("nombreUsuario"),
                        password = cifrar(result.getString("password")),
                        especialidad = result.getString("especialidad_trabajador"),
                        isResponsable = result.getBoolean("isResponsable"),
                        salario = result.getDouble("salario"),
                        fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                    )
                }
            }
        }

        trabajador?.salario = trabajador?.let { calcularSalario(it) }!!

        return trabajador
    }

    /**
     * Función que devuelve todos los trabajadores según su especialidad
     * @param especialidad Especialidad del trabajador
     * @return List<Trabajador>
     */
    override fun findByEspecialidad(especialidad: String): List<Trabajador> {
        DatabaseManager.open()
        val trabajadores = mutableListOf<Trabajador>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE especialidad_trabajador = ?"
            val result = db.select(sql, especialidad)
            result?.let{
                while(result.next()){
                    trabajadores.add(
                        Trabajador(
                            id = result.getInt("id"),
                            nombre = result.getString("nombre"),
                            telefono = result.getString("telefono"),
                            email = result.getString("email"),
                            nombreUsuario = result.getString("nombreUsuario"),
                            password = cifrar(result.getString("password")),
                            especialidad = result.getString("especialidad_trabajador"),
                            isResponsable = result.getBoolean("isResponsable"),
                            salario = result.getDouble("salario"),
                            fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                        )
                    )
                }
            }
        }
        for (trabajador in trabajadores) {
            trabajador.salario = calcularSalario(trabajador)
        }
        return trabajadores
    }

    /**
     * Función que devuelve todos los trabajadores
     * @param isResponsable Booleano que indica si el trabajador es responsable o no
     * @return List<Trabajador>
     */
    override fun findByIsResponsable(isResponsable: Boolean): List<Trabajador> {
        DatabaseManager.open()
        val trabajadores = mutableListOf<Trabajador>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE isResponsable = ?"
            val result = db.select(sql, isResponsable)
            result?.let{
                while(it.next()){
                    trabajadores.add(
                        Trabajador(
                            id = result.getInt("id"),
                            nombre = result.getString("nombre"),
                            telefono = result.getString("telefono"),
                            email = result.getString("email"),
                            nombreUsuario = result.getString("nombreUsuario"),
                            password = cifrar(result.getString("password")),
                            especialidad = result.getString("especialidad_trabajador"),
                            isResponsable = result.getBoolean("isResponsable"),
                            salario = result.getDouble("salario"),
                            fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                        )
                    )
                }
            }
        }
        for (trabajador in trabajadores) {
            trabajador.salario = calcularSalario(trabajador)
        }
        return trabajadores
    }

    /**
     * Función que devuelve todos los trabajadores
     * @return List<Trabajador>
     */
    override fun findAll(): List<Trabajador> {
        DatabaseManager.open()
        val trabajadores = mutableListOf<Trabajador>()
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador"
            val result = db.select(sql)
            result?.let{
                while(it.next()){
                    trabajadores.add(
                        Trabajador(
                            id = result.getInt("id"),
                            nombre = result.getString("nombre"),
                            telefono = result.getString("telefono"),
                            email = result.getString("email"),
                            nombreUsuario = result.getString("nombreUsuario"),
                            password = cifrar(result.getString("password")),
                            especialidad = result.getString("especialidad_trabajador"),
                            isResponsable = result.getBoolean("isResponsable"),
                            salario = result.getDouble("salario"),
                            fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion")),
                        )
                    )

                }
            }
        }

        for (trabajador in trabajadores) {
            trabajador.salario = calcularSalario(trabajador)
        }
        return trabajadores
    }

    /**
     * Función que devuelve todos los trabajadores
     * @param id Id del trabajador
     * @return Double
     */
    override fun findById(id: Long): Trabajador? {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "SELECT * FROM trabajador WHERE id = ?"
            val result = db.select(sql, id)
            result?.let{
                while(result.next()){
                    trabajador = Trabajador(
                        id = result.getInt("id"),
                        nombre = result.getString("nombre"),
                        telefono = result.getString("telefono"),
                        email = result.getString("email"),
                        nombreUsuario = result.getString("nombreUsuario"),
                        password = cifrar(result.getString("password")),
                        especialidad = result.getString("especialidad_trabajador"),
                        isResponsable = result.getBoolean("isResponsable"),
                        salario = result.getDouble("salario"),
                        fechaContratacion = LocalDate.parse(result.getString("fecha_contratacion"))
                    )
                }
            }
        }
        trabajador?.salario = trabajador?.let { calcularSalario(it) }!!
        return trabajador
    }

    /**
     * Función que devuelve todos los trabajadores
     * @param entity Trabajador
     * @return Trabajador
     */
    override fun save(entity: Trabajador): Trabajador {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "INSERT INTO trabajador(nombre, telefono, email, nombreUsuario, password, especialidad_trabajador, isResponsable, salario, fecha_contratacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            val result = db.update(sql, entity.nombre, entity.telefono, entity.email, entity.nombreUsuario, entity.password, entity.especialidad, entity.isResponsable, entity.salario, entity.fechaContratacion)
            if(result != null){
                trabajador = entity
            }
        }
        return trabajador!!
    }

    /**
     * Función que devuelve todos los trabajadores
     * @param entity Trabajador
     * @return Trabajador
     */
    override fun update(entity: Trabajador): Trabajador {
        DatabaseManager.open()
        var trabajador: Trabajador? = null
        DatabaseManager.use{ db ->
            val sql = "UPDATE trabajador SET nombre = ?, telefono = ?, email = ?, nombreUsuario = ?, password = ?, especialidad_trabajador = ?, isResponsable = ?, salario = ?, fecha_contratacion = ? WHERE id = ?"
            val result = db.update(sql, entity.nombre, entity.telefono, entity.email, entity.nombreUsuario, entity.password, entity.especialidad, entity.isResponsable, entity.salario, entity.fechaContratacion, entity.id)
            if(result != null){
                trabajador = entity
            }
        }
        return trabajador!!
    }

    /**
     * Función que devuelve todos los trabajadores
     * @param entity Trabajador
     * @return Boolean
     */
    override fun delete(entity: Trabajador): Boolean {
        DatabaseManager.open()
        var trabajadorEliminado: Boolean = false
        DatabaseManager.use{ db ->
            val sql = "DELETE FROM trabajador WHERE id = ?"
            val res = db.update(sql, entity.id)
            if(res != null){
                trabajadorEliminado = true
            }
        }
        return trabajadorEliminado
    }

    /**
     * Función que calcula el salario de un trabajador
     * @param trabajador Trabajador
     * @return Double
     */
    private fun calcularSalario(trabajador: Trabajador): Double{
        if (trabajador.salario > 0.00){
            return trabajador.salario
        }
        logger.info("Salario del trabajador con id ${trabajador.id} detectado como 0.00, calculando salario...")
        val salarioCalculado: Double
        //Por cada 3 años de antigüedad se incrementa el salario en 100€. Si no llega a 3 no se añade nada
        val antiguedad = LocalDate.now().year - trabajador.fechaContratacion.year
        val incrementoAntiguedad: Double = if (antiguedad >= 3) (antiguedad / 3) * 100.00 else 0.00
        //Si es responsable se incrementa el salario en 1000€
        val incrementoResponsable: Double = if (trabajador.isResponsable) 1000.00 else 0.00
        //Si especialidad = "ELECTRICIDAD" se incrementa el salario en 1800€, "MOTOR 1700€, "MECANICA" 1600€ y "INTERIOR" 1750€
        val incrementoEspecialidad: Double = when (trabajador.especialidad) {
            "ELECTRICIDAD" -> 1800.00
            "MOTOR" -> 1700.00
            "MECANICA" -> 1600.00
            "INTERIOR" -> 1750.00
            else -> 0.00
        }
        salarioCalculado = trabajador.salario + incrementoAntiguedad + incrementoResponsable + incrementoEspecialidad
        //Actualiza el salario en la base de datos
        logger.info("Salario del trabajador con id ${trabajador.id} calculado como $salarioCalculado, actualizándolo en la base de datos...")
        trabajador.salario = salarioCalculado
        update(trabajador)
        logger.info("Salario actualizado correctamente")
        return salarioCalculado
    }
}