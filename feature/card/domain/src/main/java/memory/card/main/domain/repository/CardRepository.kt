package memory.card.main.domain.repository

import kotlinx.coroutines.flow.Flow
import memory.card.main.domain.model.ImageHolder
import memory.card.main.domain.model.card.CardDirectory

interface CardRepository {
    /**
     * Observes a directory of cards and shallow subgroups.
     * @param groupId The ID of the parent group for which to observe the directory.
     * If `null`, the top-level directory (root group, highest in the hierarchy) is observed.
     */
    fun observeCardDirectory(groupId: Long? = null): Flow<CardDirectory>

    suspend fun addGroup(parentId: Long?, title: String)

    suspend fun addCard(
        parentId: Long,
        title: String,
        imageHolder: ImageHolder
    )
}