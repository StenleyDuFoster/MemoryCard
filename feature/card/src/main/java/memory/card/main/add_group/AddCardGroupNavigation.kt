package memory.card.main.add_group

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import memory.card.navigation.route.AddCardGroupRoute
import memory.card.navigation.route.BaseRoute

fun EntryProviderBuilder<BaseRoute>.addCardGroupScreen() {
    entry<AddCardGroupRoute> { entry ->
        AddCardGroupScreen()
    }
}