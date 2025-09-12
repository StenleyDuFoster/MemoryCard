package memory.card.core.data.data_source.room.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import memory.card.core.data.data_source.room.card.entity.CardEntity
import memory.card.core.data.data_source.room.card.entity.CardGroupEntity
import memory.card.core.data.data_source.room.card.model.GroupDirectoryDbModel
import memory.card.core.domain.model.AppConstant

@Dao
interface CardDao {
    @Query("SELECT * FROM card_group WHERE id = :id")
    fun observeGroupById(id: Long): Flow<CardGroupEntity?>

    @Query("SELECT * FROM card WHERE parent_id = :parentId")
    fun observeCardByParentId(parentId: Long): Flow<List<CardEntity>>

    @Query("SELECT * FROM card_group WHERE parent_id = :parentId")
    fun observeGroupByParentId(parentId: Long): Flow<List<CardGroupEntity>>

    @Query("SELECT id FROM card WHERE parent_id = :parentId")
    fun observeChildrenIdsOfCardByParentId(parentId: Long): Flow<List<Long>>

    @Query("SELECT id FROM card_group WHERE parent_id = :parentId")
    fun observeChildrenIdsOfGroupByParentId(parentId: Long): Flow<List<Long>>

    // TODO: update card

    // TODO: update group

    // TODO: delete card

    // TODO: delete card

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(group: CardGroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(card: CardEntity)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun observeCardDirection(
        groupId: Long?,
        json: Json,
    ): Flow<GroupDirectoryDbModel> {
        return when (groupId) {
            -1L, null -> observeTopLevelGroup(json)
            else -> observeGroupById(groupId).map { it!! }
        }.flatMapLatest { group ->
            combine(
                observeCardByParentId(group.id),
                observeGroupByParentId(group.id)
            ) { cards, groups ->
                // TODO: Change to sort order after migration to network first
                // Should have correct order because of increment of primary id
                GroupDirectoryDbModel(group, (cards + groups).sortedBy { it.id })
            }
        }
    }

    fun observeTopLevelGroup(
        json: Json,
    ) = combine(
        observeChildrenIdsOfCardByParentId(
            AppConstant.TOP_LEVEL_GROUP_ID
        ),
        observeChildrenIdsOfGroupByParentId(
            AppConstant.TOP_LEVEL_GROUP_ID
        )
    ) { cards, groups ->
        CardGroupEntity.getTopLevel(cards + groups, json)
    }
}