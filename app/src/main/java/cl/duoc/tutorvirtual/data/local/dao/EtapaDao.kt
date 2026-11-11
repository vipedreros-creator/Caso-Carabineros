package cl.duoc.tutorvirtual.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cl.duoc.tutorvirtual.data.local.entity.EtapaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EtapaDao {
    @Query("SELECT * FROM etapas ORDER BY orden")
    fun observarTodas(): Flow<List<EtapaEntity>>

    @Query("SELECT * FROM etapas WHERE id = :id")
    fun observarPorId(id: Int): Flow<EtapaEntity?>

    @Query("SELECT COUNT(*) FROM etapas")
    suspend fun contar(): Int

    @Insert
    suspend fun insertarTodas(etapas: List<EtapaEntity>)
}