package cl.duoc.tutorvirtual.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cl.duoc.tutorvirtual.data.local.entity.FaqEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FaqDao {
    @Query(
        """
        SELECT * FROM faq
        WHERE (:categoria IS NULL OR categoria = :categoria)
          AND (pregunta LIKE '%' || :texto || '%' OR respuesta LIKE '%' || :texto || '%')
        ORDER BY categoria, id
        """
    )
    fun buscar(texto: String, categoria: String?): Flow<List<FaqEntity>>

    @Query("SELECT DISTINCT categoria FROM faq ORDER BY categoria")
    fun observarCategorias(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM faq")
    suspend fun contar(): Int

    @Insert
    suspend fun insertarTodas(faqs: List<FaqEntity>)

    @Insert
    suspend fun insertar(faq: FaqEntity)
}