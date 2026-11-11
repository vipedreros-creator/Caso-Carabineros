package cl.duoc.tutorvirtual.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.duoc.tutorvirtual.data.local.entity.EntregableEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntregableDao {
    @Query("SELECT * FROM entregables WHERE usuarioId = :usuarioId")
    fun observarDeUsuario(usuarioId: Int): Flow<List<EntregableEntity>>

    @Query("SELECT * FROM entregables WHERE etapaId = :etapaId AND usuarioId = :usuarioId LIMIT 1")
    fun observar(etapaId: Int, usuarioId: Int): Flow<EntregableEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(entregable: EntregableEntity)
}