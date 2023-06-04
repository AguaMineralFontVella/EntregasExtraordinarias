package locale

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto que contiene las funciones de horas
 */
object Locale {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    /**
     * Función que devuelve una hora aleatoria entre la hora actual y la hora actual + 30 minutos
     * @param horaActual Hora actual
     * @param horaEnMediaHora Hora actual + 30 minutos
     * @return Hora aleatoria entre la hora actual y la hora actual + 30 minutos
     */
    fun getHoraAleatoria(horaActual: String, horaEnMediaHora: String): String {
        val horaActualSplit = horaActual.split(":")
        val horaEnMediaHoraSplit = horaEnMediaHora.split(":")
        val horaActualInt = horaActualSplit[0].toInt()
        val horaEnMediaHoraInt = horaEnMediaHoraSplit[0].toInt()
        val minutosActualInt = horaActualSplit[1].toInt()
        val minutosEnMediaHoraInt = horaEnMediaHoraSplit[1].toInt()
        val horaAleatoriaInt = (horaActualInt..horaEnMediaHoraInt).random()
        val minutosAleatoriosInt = if (horaAleatoriaInt == horaActualInt) {
            (minutosActualInt..59).random()
        } else if (horaAleatoriaInt == horaEnMediaHoraInt) {
            (0..minutosEnMediaHoraInt).random()
        } else {
            (0..59).random()
        }
        val horaAleatoria = if (horaAleatoriaInt < 10) { //Añadir un 0 al principio
            "0$horaAleatoriaInt"
        } else {
            "$horaAleatoriaInt"
        }
        val minutosAleatorios = if (minutosAleatoriosInt < 10) {
            "0$minutosAleatoriosInt"
        } else {
            "$minutosAleatoriosInt"
        }
        return "$horaAleatoria:$minutosAleatorios"
    }

    /**
     * Función que devuelve la fecha actual
     * @return Fecha actual
     */
    fun getFecha(): LocalDate {
        return dateFormat.parse(LocalDateTime.now().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}