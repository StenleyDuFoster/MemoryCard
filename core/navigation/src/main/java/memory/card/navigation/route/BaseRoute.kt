package memory.card.navigation.route

import java.io.Serializable

sealed interface BaseRoute : Serializable {

    companion object {
        private val routeMap = BaseRoute::class.sealedSubclasses.mapNotNull { kClass ->
            kClass.objectInstance?.let { instance ->
                instance.toString() to instance
            }
        }.toMap()

        @Throws(IllegalArgumentException::class)
        fun create(route: String): BaseRoute {
            return routeMap[route]
                ?: throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}