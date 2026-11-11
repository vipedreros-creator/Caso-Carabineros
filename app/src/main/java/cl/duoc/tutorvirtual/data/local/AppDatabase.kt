package cl.duoc.tutorvirtual.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.duoc.tutorvirtual.data.local.dao.EntregableDao
import cl.duoc.tutorvirtual.data.local.dao.EtapaDao
import cl.duoc.tutorvirtual.data.local.dao.FaqDao
import cl.duoc.tutorvirtual.data.local.dao.UsuarioDao
import cl.duoc.tutorvirtual.data.local.entity.EntregableEntity
import cl.duoc.tutorvirtual.data.local.entity.EtapaEntity
import cl.duoc.tutorvirtual.data.local.entity.FaqEntity
import cl.duoc.tutorvirtual.data.local.entity.UsuarioEntity

@Database(
    entities = [
        UsuarioEntity::class,
        EtapaEntity::class,
        EntregableEntity::class,
        FaqEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun etapaDao(): EtapaDao
    abstract fun entregableDao(): EntregableDao
    abstract fun faqDao(): FaqDao

    companion object {
        fun crear(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "tutor_virtual.db"
            ).build()
    }
}