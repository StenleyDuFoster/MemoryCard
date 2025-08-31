package memory.card.main.add_card

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import memory.card.navigation.route.AddCardRoute
import memory.card.navigation.route.BaseRoute

fun EntryProviderBuilder<BaseRoute>.addCardScreen() {
    entry<AddCardRoute> { entry ->
        AddCardScreen()
    }
}