package cl.duoc.tutorvirtual.data.repository

import cl.duoc.tutorvirtual.data.local.dao.UsuarioDao
import cl.duoc.tutorvirtual.data.local.entity.UsuarioEntity
import java.security.MessageDigest

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    /** Devuelve el usuario creado, o null si el correo ya está registrado. */
    suspend fun registrar(nombre: String, correo: String, password: String): UsuarioEntity? {
        val nuevo = UsuarioEntity(
            nombre = nombre.trim(),
            correo = correo.trim().lowercase(),
            passwordHash = hash(password)
        )
        val id = usuarioDao.insertar(nuevo)
        return if (id == -1L) null else nuevo.copy(id = id.toInt())
    }

    /** Devuelve el usuario si las credenciales son correctas, o null. */
    suspend fun iniciarSesion(correo: String, password: String): UsuarioEntity? {
        val usuario = usuarioDao.buscarPorCorreo(correo.trim().lowercase()) ?: return null
        return if (usuario.passwordHash == hash(password)) usuario else null
    }

    private fun hash(texto: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest(texto.toByteArray())
            .joinToString("") { "%02x".format(it) }
}