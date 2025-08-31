package memory.card.navigation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import memory.card.navigation.navigator.model.NavigatorMemento
import memory.card.navigation.route.BaseRoute

@Composable
fun rememberNavigator(
    initialRoute: BaseRoute,
): MemCardNavigator {
    return rememberSaveable(Unit, saver = MemCardNavigatorSaver()) {
        MemCardNavigator(
            initialRoute
        )
    }
}

private class MemCardNavigatorSaver(
) : Saver<MemCardNavigator, NavigatorMemento> {
    override fun SaverScope.save(value: MemCardNavigator): NavigatorMemento {
        return NavigatorMemento(
            initialRoute = value.initialRoute,
            backStack = value.backStack
        )
    }

    override fun restore(value: NavigatorMemento): MemCardNavigator? {
        return MemCardNavigator(
            initialRoute = value.initialRoute,
            backStack = value.backStack.toMutableStateList()
        )
    }
}