package memory.card.navigation.entry

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import memory.card.navigation.route.BaseRoute

fun <T : BaseRoute> safeNavEntry(
    key: T,
    metadata: Map<String, Any> = emptyMap(),
    content: @Composable (T) -> Unit,
): NavEntry<T> {
    return NavEntry(
        key = key,
        contentKey = key,
        metadata = metadata,
        content = content
    )
}