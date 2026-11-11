package cl.duoc.tutorvirtual.domain.validation

data class ResultadoValidacion(
    val esValido: Boolean,
    val mensaje: String? = null
) {
    companion object {
        val OK = ResultadoValidacion(true)
        fun error(mensaje: String) = ResultadoValidacion(false, mensaje)
    }
}