package memory.card.main.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import memory.card.navigation.route.GroupRoute

@Composable
fun GroupRoute.cardListScreen(
    navigateToAddCard: () -> Unit,
    navigateToAddGroup: () -> Unit,
    navigateToGroup: (Long) -> Unit,
) {
    CardListScreen(
        navigateToAddCard = navigateToAddCard,
        navigateToAddGroup = navigateToAddGroup,
        navigateToGroup = navigateToGroup,
        viewModel = hiltViewModel<CardListViewModel, CardListViewModel.Factory>(
            key = groupId.toString(),
        ) { factory ->
            factory.create(groupId)
        }
    )
}