package memory.card.main.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import memory.card.main.domain.model.ImageHolder
import memory.card.main.domain.model.card.CardDirectory
import memory.card.main.domain.model.card.CardDirectoryItem
import memory.card.main.list.item.CardGroupItem
import memory.card.main.list.item.CardItem

@Composable
internal fun CardListScreen(
    navigateToAddCard: () -> Unit,
    navigateToAddGroup: () -> Unit,
    navigateToGroup: (Long) -> Unit,
    viewModel: CardListViewModel = hiltViewModel(),
) {
    val directory by viewModel.directory.collectAsStateWithLifecycle()

    CardListScreen(
        directory = directory,
        navigateToAddCard = navigateToAddCard,
        navigateToAddGroup = navigateToAddGroup,
        navigateToGroup = navigateToGroup
    )
}

@Composable
private fun CardListScreen(
    directory: CardDirectory?,
    navigateToAddCard: () -> Unit = {},
    navigateToAddGroup: () -> Unit = {},
    navigateToGroup: (Long) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        directory?.title?.let {
            BasicText(
                it,
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = 8.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                60.dp
            ),
            contentPadding = PaddingValues(
                horizontal = 12.dp
            ),
            modifier = Modifier
                .fillMaxSize()
        ) {
            directory?.children?.let { children ->
                items(
                    items = children,
                    key = { item ->
                        when (item) {
                            is CardDirectoryItem.Card -> "Card" + item.id
                            is CardDirectoryItem.Group -> "Group" + item.id
                        }
                    },
                    contentType = { item -> item }
                ) { item ->
                    when (item) {
                        is CardDirectoryItem.Card -> {
                            CardItem(
                                item = item,
                                modifier = Modifier
                                    .animateItem()
                            )
                        }

                        is CardDirectoryItem.Group -> {
                            CardGroupItem(
                                item = item,
                                navigateToGroup = navigateToGroup,
                                modifier = Modifier
                                    .animateItem()
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        CardDirectory(
            title = "Title",
            children = listOf(
                CardDirectoryItem.Card(
                    id = 7409, imageHolder = ImageHolder(
                        ""
                    ),
                    title = "facilisi"
                ),
                CardDirectoryItem.Group(
                    id = 5160,
                    title = "nonumes",
                    children = listOf(
                        12L, 14L
                    )
                )
            )
        )
    )
}