package cl.duoc.tutorvirtual.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import cl.duoc.tutorvirtual.domain.model.EstadoEntregable

@Entity(
    tableName = "entregables",
    foreignKeys = [
        ForeignKey(
            entity = EtapaEntity::class,
            parentColumns = ["id"],
            childColumns = ["etapaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("etapaId"),
        Index("usuarioId"),
        Index(value = ["etapaId", "usuarioId"], unique = true)
    ]
)
data class EntregableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val etapaId: Int,
    val usuarioId: Int,
    val estado: EstadoEntregable = EstadoEntregable.POR_INICIAR,
    val notas: String = "",
    val evidenciaUri: String? = null,
    val actualizadoEn: Long = 0L
)