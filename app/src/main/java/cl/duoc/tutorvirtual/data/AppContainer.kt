package cl.duoc.tutorvirtual.data

import android.content.Context
import cl.duoc.tutorvirtual.data.local.AppDatabase
import cl.duoc.tutorvirtual.data.repository.ContenidoRepository
import cl.duoc.tutorvirtual.data.repository.UsuarioRepository

class AppContainer(context: Context) {
    private val database = AppDatabase.crear(context)

    val usuarioRepository = UsuarioRepository(database.usuarioDao())

    val contenidoRepository = ContenidoRepository(
        etapaDao = database.etapaDao(),
        faqDao = database.faqDao(),
        entregableDao = database.entregableDao()
    )

    val sesion = SesionManager(context)
}