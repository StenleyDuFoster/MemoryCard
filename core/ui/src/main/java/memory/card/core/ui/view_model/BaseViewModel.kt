package memory.card.core.ui.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    protected val ioDispatcher: CoroutineDispatcher,
    protected val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {
    protected val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { context, throwable ->
            handleError(
                context,
                throwable
            )
        }

    open fun handleError(context: CoroutineContext, throwable: Throwable) {
        throwable.printStackTrace()
    }
}