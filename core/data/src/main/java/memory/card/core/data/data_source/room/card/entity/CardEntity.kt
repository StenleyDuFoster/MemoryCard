package memory.card.core.data.data_source.room.card.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "card",
    indices = [
        Index(value = ["parent_id"])
    ]
)
class CardEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    @ColumnInfo(name = "parent_id")
    override val parentId: Long,
    val imageUrl: String? = null,
    val imagePath: String? = null,
    val title: String,
) : CardDirectoryDbItem {
    init {
        require(imageUrl != null || imagePath != null)
    }
}