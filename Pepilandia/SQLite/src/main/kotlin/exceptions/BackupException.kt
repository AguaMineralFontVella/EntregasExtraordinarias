package exceptions

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona las excepciones de los backups
 * @property message String
 */
sealed class BackupException(val message: String) {
    class NoValido(message: String) : BackupException(" ERROR: Backup no válido: $message")
    class NoGuardado(message: String) : BackupException(" ERROR: Backup no guardado: $message")
    class NoEncontrado(message: String) : BackupException(" ERROR: Archivo para guardar el backup no encontrado: $message")
}
