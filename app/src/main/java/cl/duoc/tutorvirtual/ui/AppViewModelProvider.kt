package cl.duoc.tutorvirtual.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cl.duoc.tutorvirtual.TutorApp
import cl.duoc.tutorvirtual.ui.screens.login.LoginViewModel
import cl.duoc.tutorvirtual.ui.screens.registro.RegistroViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RegistroViewModel(tutorApp().container.usuarioRepository, tutorApp().container.sesion)
        }
        initializer {
            LoginViewModel(tutorApp().container.usuarioRepository, tutorApp().container.sesion)
        }
    }
}

private fun CreationExtras.tutorApp(): TutorApp = this[APPLICATION_KEY] as TutorApp