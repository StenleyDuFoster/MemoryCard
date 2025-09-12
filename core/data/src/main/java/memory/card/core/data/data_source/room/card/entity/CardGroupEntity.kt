package memory.card.core.data.data_source.room.card.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.json.Json
import memory.card.core.domain.model.AppConstant

@Entity(
    tableName = "card_group",
    indices = [
        Index(value = ["parent_id"])
    ],
)
data class CardGroupEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    @ColumnInfo(name = "parent_id")
    override val parentId: Long? = null,
    val title: String? = null,
    var children: String = "[]",
) : CardDirectoryDbItem {
    companion object {
        private val TOP_LEVEL = CardGroupEntity(
            id = AppConstant.TOP_LEVEL_GROUP_ID,
            parentId = null,
            title = null
        )

        fun getTopLevel(
            children: List<Long>,
            json: Json,
        ): CardGroupEntity {
            return TOP_LEVEL.copy(
                children = json.encodeToString(children)
            )
        }
    }
}