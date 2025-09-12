package memory.card.main.list.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.card.main.domain.model.card.CardDirectoryItem

@Composable
internal fun CardGroupItem(
    item: CardDirectoryItem.Group,
    navigateToGroup: (Long) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable {
                navigateToGroup(item.id)
            }
    ) {
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Red)
        ) {
            BasicText(
                item.children.count().toString(),
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
            )
        }

        item.title?.let {
            BasicText(
                it,
                modifier = Modifier
                    .padding(
                        top = 4.dp
                    )
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CardGroupItem(
        CardDirectoryItem.Group(
            id = 7409,
            title = "nonumes",
            children = listOf(
                12L, 14L
            )
        )
    )

}