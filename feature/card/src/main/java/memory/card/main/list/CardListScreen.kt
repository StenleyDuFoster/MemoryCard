package memory.card.main.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState

@Composable
fun CardListScreen(
    testNavigate: () -> Unit,
    viewModel: CardListViewModel = hiltViewModel(),
) {
    val a = LocalLifecycleOwner.current
    val text by viewModel.flow.collectAsState("INITIAL")
    val textL by viewModel.flow.collectAsStateWithLifecycle("INITIAL")
    val state = a.lifecycle.currentStateAsState()

    LaunchedEffect(state.value) {
        Log.v("112233 state", state.value.name)
    }
    LaunchedEffect(a.lifecycle) {
        Log.v("112233 state", a.lifecycle.toString())
    }

    Column(Modifier.statusBarsPadding()) {
        BasicText(
            text,
            modifier = Modifier
        )
        BasicText(
            textL,
            modifier = Modifier
        )
        BasicText(
            state.value.name,
            modifier = Modifier
        )
        BasicText(
            "Navigate",
            modifier = Modifier.clickable {
                testNavigate.invoke()
            }
        )
    }

}

@Composable
private fun RunCode(text: String) {
    BasicText(
        text,
        modifier = Modifier.statusBarsPadding()
    )
}