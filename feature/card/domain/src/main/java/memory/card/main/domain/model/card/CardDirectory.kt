package memory.card.main.domain.model.card

class CardDirectory(
    val id: Long? = null,
    val title: String? = null,
    val children: List<CardDirectoryItem>
)