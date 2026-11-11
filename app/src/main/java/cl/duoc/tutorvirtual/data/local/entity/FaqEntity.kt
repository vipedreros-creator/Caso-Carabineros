package cl.duoc.tutorvirtual.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "faq")
data class FaqEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pregunta: String,
    val categoria: String,
    val respuesta: String
)