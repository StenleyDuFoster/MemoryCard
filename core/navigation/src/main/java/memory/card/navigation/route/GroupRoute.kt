package memory.card.navigation.route

import kotlinx.serialization.Serializable
import memory.card.core.domain.model.AppConstant

@Serializable
data class GroupRoute(
    val groupId: Long = AppConstant.TOP_LEVEL_GROUP_ID,
): BaseRoute