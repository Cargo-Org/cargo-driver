package com.example.carog_driver.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface Route {
    /* if screen will take argument example :
    @Serializable
    data class MapRoute(val isFromSetting : Boolean = false) : Route

    if screen not have argument example:
    @Serializable
    object FavoriteRoute : Route
    * */

    @Serializable
    object RegisterRoute: Route

    @Serializable
    object LoginRoute : Route
}