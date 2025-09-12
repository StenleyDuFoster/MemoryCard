package memory.card.core.data.data_source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import memory.card.core.data.data_source.room.card.CardDao
import memory.card.core.data.data_source.room.card.entity.CardEntity
import memory.card.core.data.data_source.room.card.entity.CardGroupEntity

@Database(
    entities = [
        CardEntity::class,
        CardGroupEntity::class
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = false
)
internal abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "app_database"
    }

    abstract fun getCardDao(): CardDao
}