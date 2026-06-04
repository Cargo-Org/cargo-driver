package com.example.carog_driver.presentation.util

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

sealed interface UiText {
    data class Resource(val resource: StringResource) : UiText
    data class ResourceWithArgs(val resource: StringResource,
                                val args: List<Any>) : UiText
}


suspend fun UiText.asString(): String {
    return when (this) {
        is UiText.Resource -> getString(resource)
        is UiText.ResourceWithArgs -> getString(
            resource,
            *args.toTypedArray()
        )
    }
}