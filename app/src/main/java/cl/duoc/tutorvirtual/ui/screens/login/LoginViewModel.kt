package cl.duoc.tutorvirtual.ui.screens.login

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

data class LoginUiState(
    val correo: String = "",
    val password: String = "",
    val errorCorreo: String? = null,
    val errorPassword: String? = null,
    val errorGeneral: String? = null,
    val botonHabilitado: Boolean = false,
    val cargando: Boolean = false,
    val loginExitoso: Boolean = false
)

class LoginViewModel(
    private val usuarioRepository: UsuarioRepository,
    private val sesion: SesionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onCorreoChange(valor: String) = _uiState.update {
        it.copy(correo = valor, errorCorreo = Validadores.correo(valor).mensaje, errorGeneral = null).recalcular()
    }

    fun onPasswordChange(valor: String) = _uiState.update {
        it.copy(password = valor, errorPassword = Validadores.passwordLogin(valor).mensaje, errorGeneral = null).recalcular()
    }

    fun iniciarSesion() {
        val s = _uiState.value
        if (!s.botonHabilitado) {
            _uiState.update {
                it.copy(
                    errorCorreo = Validadores.correo(it.correo).mensaje,
                    errorPassword = Validadores.passwordLogin(it.password).mensaje
                )
            }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true).recalcular() }
            val usuario = usuarioRepository.iniciarSesion(s.correo, s.password)
            if (usuario == null) {
                _uiState.update {
                    it.copy(cargando = false, errorGeneral = "Correo o contraseña incorrectos").recalcular()
                }
            } else {
                sesion.iniciar(usuario.id, usuario.nombre)
                _uiState.update { it.copy(cargando = false, loginExitoso = true) }
            }
        }
    }

    private fun LoginUiState.recalcular(): LoginUiState = copy(
        botonHabilitado = !cargando &&
                Validadores.correo(correo).esValido &&
                Validadores.passwordLogin(password).esValido
    )
}
