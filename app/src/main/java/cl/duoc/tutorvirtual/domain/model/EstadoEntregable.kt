package cl.duoc.tutorvirtual.domain.model

enum class EstadoEntregable(val etiqueta: String) {
    POR_INICIAR("Por iniciar"),
    EN_DESARROLLO("En desarrollo"),
    ENVIADO("Enviado"),
    APROBADO("Aprobado")
}