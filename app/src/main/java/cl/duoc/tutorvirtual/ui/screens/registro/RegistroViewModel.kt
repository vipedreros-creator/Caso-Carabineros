package cl.duoc.tutorvirtual.ui.screens.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.tutorvirtual.data.SesionManager
import cl.duoc.tutorvirtual.data.repository.UsuarioRepository
import cl.duoc.tutorvirtual.domain.validation.Validadores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistroUiState(
    val nombre: String = "",
    val correo: String = "",
    val password: String = "",
    val confirmacion: String = "",
    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorPassword: String? = null,
    val errorConfirmacion: String? = null,
    val botonHabilitado: Boolean = false,
    val cargando: Boolean = false,
    val registroExitoso: Boolean = false
)

class RegistroViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val sesion: SesionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onNombreChange(valor: String) = _uiState.update {
        it.copy(nombre = valor, errorNombre = Validadores.nombre(valor).mensaje).recalcular()
    }

    fun onCorreoChange(valor: String) = _uiState.update {
        it.copy(correo = valor, errorCorreo = Validadores.correo(valor).mensaje).recalcular()
    }

    fun onPasswordChange(valor: String) = _uiState.update {
        val errorConf = if (it.confirmacion.isNotEmpty())
            Validadores.confirmacion(valor, it.confirmacion).mensaje else it.errorConfirmacion
        it.copy(
            password = valor,
            errorPassword = Validadores.password(valor).mensaje,
            errorConfirmacion = errorConf
        ).recalcular()
    }

    fun onConfirmacionChange(valor: String) = _uiState.update {
        it.copy(
            confirmacion = valor,
            errorConfirmacion = Validadores.confirmacion(it.password, valor).mensaje
        ).recalcular()
    }

    fun registrar() {
        val s = _uiState.value
        if (!s.botonHabilitado) {
            // Muestra todos los errores si el usuario presiona con campos vacíos
            _uiState.update {
                it.copy(
                    errorNombre = Validadores.nombre(it.nombre).mensaje,
                    errorCorreo = Validadores.correo(it.correo).mensaje,
                    errorPassword = Validadores.password(it.password).mensaje,
                    errorConfirmacion = Validadores.confirmacion(it.password, it.confirmacion).mensaje
                )
            }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true).recalcular() }
            val usuario = usuarioRepository.registrar(s.nombre, s.correo, s.password)
            if (usuario == null) {
                _uiState.update {
                    it.copy(cargando = false, errorCorreo = "Este correo ya está registrado").recalcular()
                }
            } else {
                sesion.iniciar(usuario.id, usuario.nombre)
                _uiState.update { it.copy(cargando = false, registroExitoso = true) }
            }
        }
    }

    private fun RegistroUiState.recalcular(): RegistroUiState = copy(
        botonHabilitado = !cargando &&
                Validadores.nombre(nombre).esValido &&
                Validadores.correo(correo).esValido &&
                Validadores.password(password).esValido &&
                Validadores.confirmacion(password, confirmacion).esValido
    )
}