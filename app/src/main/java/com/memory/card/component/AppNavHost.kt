package com.memory.card.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import memory.card.main.add_card.addCardScreen
import memory.card.main.add_group.addCardGroupScreen
import memory.card.main.list.cardListScreen
import memory.card.navigation.entry.safeNavEntry
import memory.card.navigation.navigator.MemCardNavigator
import memory.card.navigation.route.AddCardGroupRoute
import memory.card.navigation.route.AddCardRoute
import memory.card.navigation.route.GroupRoute

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
internal fun AppNavHost(
    navigator: MemCardNavigator,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = navigator.backStack,
        onBack = { navigator.goBack() },
        entryProvider = {
            when (val route = it) {
                is AddCardGroupRoute -> safeNavEntry(route) {
                    route.addCardGroupScreen()
                }


                is AddCardRoute -> safeNavEntry(route) {
                    route.addCardScreen()
                }

                is GroupRoute -> safeNavEntry(route) {
                    route.cardListScreen(
                        navigateToAddCard = {
                            navigator.goTo(AddCardRoute)
                        },
                        navigateToAddGroup = {
                            navigator.goTo(AddCardGroupRoute)
                        },
                        navigateToGroup = {
                            navigator.goTo(GroupRoute(it))
                        }
                    )
                }

                else -> safeNavEntry(it) {
                    Box {
                        Text(
                            text = "Incorrect route",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        modifier = modifier
    )
}