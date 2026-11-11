package cl.duoc.tutorvirtual.data.repository

import cl.duoc.tutorvirtual.data.local.SeedData
import cl.duoc.tutorvirtual.data.local.dao.EntregableDao
import cl.duoc.tutorvirtual.data.local.dao.EtapaDao
import cl.duoc.tutorvirtual.data.local.dao.FaqDao
import cl.duoc.tutorvirtual.data.local.entity.EntregableEntity
import cl.duoc.tutorvirtual.data.local.entity.EtapaEntity
import cl.duoc.tutorvirtual.data.local.entity.FaqEntity
import kotlinx.coroutines.flow.Flow

class ContenidoRepository(
    private val etapaDao: EtapaDao,
    private val faqDao: FaqDao,
    private val entregableDao: EntregableDao
) {
    // Etapas
    fun etapas(): Flow<List<EtapaEntity>> = etapaDao.observarTodas()
    fun etapa(id: Int): Flow<EtapaEntity?> = etapaDao.observarPorId(id)

    // FAQ
    fun buscarFaq(texto: String, categoria: String?): Flow<List<FaqEntity>> =
        faqDao.buscar(texto.trim(), categoria)
    fun categoriasFaq(): Flow<List<String>> = faqDao.observarCategorias()
    suspend fun agregarFaq(faq: FaqEntity) = faqDao.insertar(faq)

    // Entregables
    fun entregablesDe(usuarioId: Int): Flow<List<EntregableEntity>> =
        entregableDao.observarDeUsuario(usuarioId)
    fun entregable(etapaId: Int, usuarioId: Int): Flow<EntregableEntity?> =
        entregableDao.observar(etapaId, usuarioId)
    suspend fun guardarEntregable(entregable: EntregableEntity) =
        entregableDao.guardar(entregable.copy(actualizadoEn = System.currentTimeMillis()))

    // Datos iniciales
    suspend fun sembrarSiVacio() {
        if (etapaDao.contar() == 0) etapaDao.insertarTodas(SeedData.etapas())
        if (faqDao.contar() == 0) faqDao.insertarTodas(SeedData.faqs())
    }
}