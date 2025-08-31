package memory.card.navigation.navigator.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import memory.card.navigation.route.BaseRoute

@Parcelize
internal class NavigatorMemento(
    val initialRoute: BaseRoute,
    val backStack: List<BaseRoute>,
) : Parcelable