package memory.card.main.domain.model

class ImageHolder(
    val imageUrl: String? = null,
    val imagePath: String? = null
) {
    init {
        require(imageUrl != null || imagePath != null)
    }
}