package cl.duoc.tutorvirtual.data.local

import cl.duoc.tutorvirtual.data.local.entity.EtapaEntity
import cl.duoc.tutorvirtual.data.local.entity.FaqEntity
import java.time.LocalDate
import java.time.ZoneId

object SeedData {

    private fun enSemanas(semanas: Long): Long =
        LocalDate.now().plusWeeks(semanas)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant().toEpochMilli()

    fun etapas(): List<EtapaEntity> = listOf(
        EtapaEntity(
            orden = 1, titulo = "Planteamiento del problema",
            descripcion = "Define el problema, los objetivos y la justificación de tu investigación.",
            formatoRequerido = "Documento Word/PDF, máx. 5 páginas, norma APA 7.",
            guia = "1. Describe el contexto.\n2. Formula el problema como pregunta.\n3. Define objetivo general y específicos.\n4. Justifica la relevancia del estudio.",
            rubrica = "• Problema claro y delimitado\n• Objetivos medibles\n• Justificación fundamentada",
            fechaLimite = enSemanas(2)
        ),
        EtapaEntity(
            orden = 2, titulo = "Marco teórico",
            descripcion = "Revisa y organiza los conceptos y estudios que sustentan tu trabajo.",
            formatoRequerido = "Documento PDF, citas y referencias en APA 7.",
            guia = "1. Identifica conceptos clave.\n2. Busca fuentes académicas confiables.\n3. Organiza por temas, no por autores.\n4. Cita correctamente.",
            rubrica = "• Fuentes pertinentes y actuales\n• Organización lógica\n• Citación correcta",
            fechaLimite = enSemanas(4)
        ),
        EtapaEntity(
            orden = 3, titulo = "Metodología",
            descripcion = "Define el enfoque, diseño, población y técnicas de tu investigación.",
            formatoRequerido = "Documento PDF, máx. 6 páginas.",
            guia = "1. Elige enfoque (cuantitativo, cualitativo, mixto).\n2. Define población y muestra.\n3. Describe técnicas e instrumentos.\n4. Indica consideraciones éticas.",
            rubrica = "• Coherencia con los objetivos\n• Instrumentos descritos\n• Consideraciones éticas",
            fechaLimite = enSemanas(6)
        ),
        EtapaEntity(
            orden = 4, titulo = "Recogida de información",
            descripcion = "Aplica tus instrumentos y registra los datos obtenidos.",
            formatoRequerido = "Informe de avance con evidencias adjuntas.",
            guia = "1. Aplica los instrumentos según lo planificado.\n2. Registra los datos de forma ordenada.\n3. Respalda las evidencias.",
            rubrica = "• Datos completos y ordenados\n• Evidencias de respaldo",
            fechaLimite = enSemanas(8)
        ),
        EtapaEntity(
            orden = 5, titulo = "Análisis de resultados",
            descripcion = "Procesa e interpreta los datos para responder a tus objetivos.",
            formatoRequerido = "Documento PDF con tablas y gráficos.",
            guia = "1. Procesa los datos.\n2. Presenta tablas y gráficos claros.\n3. Interpreta en relación con los objetivos.",
            rubrica = "• Análisis coherente con los objetivos\n• Presentación clara de resultados",
            fechaLimite = enSemanas(10)
        ),
        EtapaEntity(
            orden = 6, titulo = "Conclusiones y entrega final",
            descripcion = "Cierra el trabajo con conclusiones, recomendaciones y revisión del formato.",
            formatoRequerido = "Informe final completo en PDF, norma APA 7.",
            guia = "1. Responde a cada objetivo.\n2. Indica limitaciones y proyecciones.\n3. Revisa formato, citas y ortografía.",
            rubrica = "• Conclusiones alineadas a objetivos\n• Formato y redacción académica",
            fechaLimite = enSemanas(12)
        )
    )

    fun faqs(): List<FaqEntity> = listOf(
        FaqEntity(
            pregunta = "¿Cómo cito un libro en APA 7?",
            categoria = "Citación",
            respuesta = "En el texto: (Apellido, año). En referencias: Apellido, A. (Año). Título del libro en cursiva. Editorial."
        ),
        FaqEntity(
            pregunta = "¿Cómo cito una página web?",
            categoria = "Citación",
            respuesta = "Apellido, A. (Fecha). Título de la página. Nombre del sitio. URL"
        ),
        FaqEntity(
            pregunta = "¿Cómo defino el enfoque de mi investigación?",
            categoria = "Metodología",
            respuesta = "Cuantitativo si mides variables con números; cualitativo si exploras experiencias y significados; mixto si combinas ambos. Debe ser coherente con tus objetivos."
        ),
        FaqEntity(
            pregunta = "¿Cuál es la diferencia entre objetivo general y específicos?",
            categoria = "Metodología",
            respuesta = "El general expresa el propósito global del estudio. Los específicos son los pasos medibles que permiten alcanzarlo."
        ),
        FaqEntity(
            pregunta = "¿Qué formato de letra y márgenes debo usar?",
            categoria = "Formato",
            respuesta = "Revisa la pauta de tu profesor guía. Como referencia general: fuente legible de 11 o 12 pt, interlineado 1,5 y márgenes de 2,5 cm."
        ),
        FaqEntity(
            pregunta = "¿Cómo debe estructurarse un informe de avance?",
            categoria = "Formato",
            respuesta = "Portada, índice, desarrollo por capítulos según la etapa, referencias en APA y anexos si corresponde."
        )
    )
}