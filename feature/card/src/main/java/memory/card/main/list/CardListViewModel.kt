package memory.card.main.list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import memory.card.core.domain.model.AppDispatcher
import memory.card.core.domain.model.Dispatcher
import memory.card.core.ui.view_model.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CardListViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.Default) defaultDispatcher: CoroutineDispatcher,
) : BaseViewModel(
    ioDispatcher = ioDispatcher,
    defaultDispatcher = defaultDispatcher
) {

    val flow = flow {
        var counter = 0
        while (true) {
            counter++
            emit(counter.toString())
            delay(1000)
        }
    }
}