package memory.card.navigation.navigator

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import memory.card.navigation.route.BaseRoute

class MemCardNavigator internal constructor(
    val initialRoute: BaseRoute,
    val backStack: SnapshotStateList<BaseRoute> = mutableStateListOf(initialRoute),
) {
    fun goBack() {
        backStack.removeLastOrNull()
    }

    fun goTo(route: BaseRoute) {
        backStack.add(route)
    }
}