package memory.card.core.data.data_source.room.card.entity

sealed interface CardDirectoryDbItem {
    val id: Long
    val parentId: Long?
}