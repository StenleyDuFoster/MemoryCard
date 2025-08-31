package memory.card.main.list

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import memory.card.navigation.route.BaseRoute
import memory.card.navigation.route.CardListRoute

fun EntryProviderBuilder<BaseRoute>.cardListScreen(
    testNavigate: () -> Unit,
) {
    entry<CardListRoute> { entry ->
        CardListScreen(testNavigate)
    }
}