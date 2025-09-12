package memory.card.main.list.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import memory.card.main.domain.model.ImageHolder
import memory.card.main.domain.model.card.CardDirectoryItem

@Composable
internal fun CardItem(
    item: CardDirectoryItem.Card,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Red)
        ) {

        }

        BasicText(
            item.title,
            modifier = Modifier
                .padding(
                    top = 4.dp
                )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CardItem(
        CardDirectoryItem.Card(
            id = 7409,
            imageHolder = ImageHolder(
                ""
            ),
            title = "facilisi"
        )
    )

}