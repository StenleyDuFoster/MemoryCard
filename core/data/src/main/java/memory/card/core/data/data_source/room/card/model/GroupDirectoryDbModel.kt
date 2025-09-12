package memory.card.core.data.data_source.room.card.model

import memory.card.core.data.data_source.room.card.entity.CardDirectoryDbItem
import memory.card.core.data.data_source.room.card.entity.CardGroupEntity

class GroupDirectoryDbModel(
    val id: Long?,
    val title: String?,
    val children: List<CardDirectoryDbItem>,
) {
    constructor(
        group: CardGroupEntity,
        children: List<CardDirectoryDbItem>
    ) : this(
        id = group.id,
        title = group.title,
        children = children
    )
}