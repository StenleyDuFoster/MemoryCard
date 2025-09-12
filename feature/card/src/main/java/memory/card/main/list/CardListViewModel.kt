package memory.card.main.list

import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import memory.card.core.domain.model.AppDispatcher
import memory.card.core.domain.model.Dispatcher
import memory.card.core.ui.view_model.BaseViewModel
import memory.card.main.domain.use_case.ObserveCardDirectoryUseCase

@HiltViewModel(assistedFactory = CardListViewModel.Factory::class)
internal class CardListViewModel @AssistedInject constructor(
    observeCardDirectoryUseCase: ObserveCardDirectoryUseCase,
    @Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
    @Dispatcher(AppDispatcher.Default) defaultDispatcher: CoroutineDispatcher,
    @Assisted private val groupId: Long,
) : BaseViewModel(
    ioDispatcher = ioDispatcher,
    defaultDispatcher = defaultDispatcher
) {
    val directory = observeCardDirectoryUseCase(groupId)
        .catch {
            handleError(currentCoroutineContext(), it)
        }
        .flowOn(defaultDispatcher)
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = null
        )

    @AssistedFactory
    interface Factory {
        fun create(
            groupId: Long,
        ): CardListViewModel
    }
}