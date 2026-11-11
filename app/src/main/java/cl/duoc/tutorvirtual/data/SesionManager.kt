package cl.duoc.tutorvirtual.data

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SesionManager(context: Context) {
    private val prefs = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)

    private val _usuarioId = MutableStateFlow(prefs.getInt(KEY_ID, SIN_SESION))
    val usuarioId: StateFlow<Int> = _usuarioId.asStateFlow()

    val nombre: String get() = prefs.getString(KEY_NOMBRE, "") ?: ""

    fun iniciar(id: Int, nombre: String) {
        prefs.edit().putInt(KEY_ID, id).putString(KEY_NOMBRE, nombre).apply()
        _usuarioId.value = id
    }

    fun cerrar() {
        prefs.edit().clear().apply()
        _usuarioId.value = SIN_SESION
    }

    companion object {
        const val SIN_SESION = -1
        private const val KEY_ID = "usuario_id"
        private const val KEY_NOMBRE = "usuario_nombre"
    }
}