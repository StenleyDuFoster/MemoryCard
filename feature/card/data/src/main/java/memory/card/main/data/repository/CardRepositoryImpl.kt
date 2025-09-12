package memory.card.main.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import memory.card.core.data.data_source.room.card.CardDao
import memory.card.core.data.data_source.room.card.entity.CardEntity
import memory.card.core.data.data_source.room.card.entity.CardGroupEntity
import memory.card.main.domain.model.ImageHolder
import memory.card.main.domain.model.card.CardDirectory
import memory.card.main.domain.model.card.CardDirectoryItem
import memory.card.main.domain.repository.CardRepository

internal class CardRepositoryImpl(
    private val cardDao: CardDao,
    private val json: Json,
) : CardRepository {

    override fun observeCardDirectory(groupId: Long?): Flow<CardDirectory> {
        return cardDao.observeCardDirection(
            groupId,
            json
        ).map { model ->
            CardDirectory(
                model.id,
                model.title,
                model.children.map { modelItem ->
                    when (modelItem) {
                        is CardEntity -> {
                            CardDirectoryItem.Card(
                                modelItem.id,
                                ImageHolder(
                                    modelItem.imageUrl,
                                    modelItem.imagePath,
                                ),
                                modelItem.title
                            )
                        }

                        is CardGroupEntity -> {
                            CardDirectoryItem.Group(
                                modelItem.id,
                                modelItem.title,
                                json.decodeFromString(modelItem.children)
                            )
                        }
                    }
                }
            )
        }
    }

    override suspend fun addGroup(parentId: Long?, title: String) {
        cardDao.addGroup(
            CardGroupEntity(
                parentId = parentId,
                title = title
            )
        )
    }

    override suspend fun addCard(
        parentId: Long,
        title: String,
        imageHolder: ImageHolder,
    ) {
        cardDao.addCard(
            CardEntity(
                parentId = parentId,
                title = title,
                imageUrl = imageHolder.imageUrl,
                imagePath = imageHolder.imagePath
            )
        )
    }
}