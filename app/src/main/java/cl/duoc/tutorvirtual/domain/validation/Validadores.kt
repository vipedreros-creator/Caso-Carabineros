package cl.duoc.tutorvirtual.domain.validation

import cl.duoc.tutorvirtual.domain.validation.ResultadoValidacion.Companion.OK
import cl.duoc.tutorvirtual.domain.validation.ResultadoValidacion.Companion.error

object Validadores {

    private val REGEX_CORREO = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun nombre(valor: String): ResultadoValidacion = when {
        valor.isBlank() -> error("El nombre es obligatorio")
        valor.trim().length < 3 -> error("Debe tener al menos 3 caracteres")
        !valor.all { it.isLetter() || it.isWhitespace() } -> error("Solo se permiten letras y espacios")
        else -> OK
    }

    fun correo(valor: String): ResultadoValidacion = when {
        valor.isBlank() -> error("El correo es obligatorio")
        !REGEX_CORREO.matches(valor.trim()) -> error("Ingresa un correo válido")
        else -> OK
    }

    fun password(valor: String): ResultadoValidacion = when {
        valor.isEmpty() -> error("La contraseña es obligatoria")
        valor.length < 8 -> error("Debe tener al menos 8 caracteres")
        valor.none { it.isUpperCase() } -> error("Debe incluir una mayúscula")
        valor.none { it.isDigit() } -> error("Debe incluir un número")
        else -> OK
    }

    fun confirmacion(password: String, confirmacion: String): ResultadoValidacion = when {
        confirmacion.isEmpty() -> error("Confirma tu contraseña")
        confirmacion != password -> error("Las contraseñas no coinciden")
        else -> OK
    }

    fun passwordLogin(valor: String): ResultadoValidacion =
        if (valor.isEmpty()) error("La contraseña es obligatoria") else OK

    fun textoObligatorio(valor: String, campo: String, minimo: Int = 3): ResultadoValidacion = when {
        valor.isBlank() -> error("$campo es obligatorio")
        valor.trim().length < minimo -> error("$campo debe tener al menos $minimo caracteres")
        else -> OK
    }
}