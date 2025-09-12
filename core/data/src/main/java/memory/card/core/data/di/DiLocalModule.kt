package memory.card.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import memory.card.core.data.data_source.room.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DiLocalModule {

    @Provides
    @Singleton
    fun provideSerializer() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideAppDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCardDao(
        db: AppDatabase
    ) = db.getCardDao()
}