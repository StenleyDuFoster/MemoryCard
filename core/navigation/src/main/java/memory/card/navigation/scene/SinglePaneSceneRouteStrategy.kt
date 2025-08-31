package memory.card.navigation.scene

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import memory.card.navigation.route.BaseRoute

class SinglePaneSceneRouteStrategy : SceneStrategy<BaseRoute> {

    @Composable
    override fun calculateScene(
        entries: List<NavEntry<BaseRoute>>,
        onBack: (Int) -> Unit,
    ): Scene<BaseRoute>? {
        return SinglePaneScene(
            key = BaseRoute.create(entries.last().contentKey as String),
            entry = entries.last(),
            previousEntries = entries.dropLast(1),
        )
    }
}

internal data class SinglePaneScene<T : BaseRoute>(
    override val key: T,
    val entry: NavEntry<T>,
    override val previousEntries: List<NavEntry<T>>,
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(entry)

    override val content: @Composable () -> Unit = { entry.Content() }
}