package memory.card.main.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import memory.card.core.data.data_source.room.card.CardDao
import memory.card.main.data.repository.CardRepositoryImpl
import memory.card.main.domain.repository.CardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DiModule {

    @Singleton
    @Provides
    fun provideCardRepository(
        cardDao: CardDao,
        json: Json,
    ): CardRepository = CardRepositoryImpl(
        cardDao = cardDao,
        json = json
    )
}