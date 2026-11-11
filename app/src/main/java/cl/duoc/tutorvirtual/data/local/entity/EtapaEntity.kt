package cl.duoc.tutorvirtual.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "etapas")
data class EtapaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orden: Int,
    val titulo: String,
    val descripcion: String,
    val formatoRequerido: String,
    val guia: String,
    val rubrica: String,
    val fechaLimite: Long
)