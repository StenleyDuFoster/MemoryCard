package memory.card.main.domain.model.card

import memory.card.main.domain.model.ImageHolder

sealed interface CardDirectoryItem {
    val id: Long

    data class Card(
        override val id: Long,
        val imageHolder: ImageHolder,
        val title: String
    ): CardDirectoryItem

    data class Group(
        override val id: Long,
        val title: String?,
        val children: List<Long>
    ): CardDirectoryItem
}