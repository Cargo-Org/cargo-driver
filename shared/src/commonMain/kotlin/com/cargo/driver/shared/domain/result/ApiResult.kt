package com.cargo.driver.shared.domain.result

import com.cargo.driver.shared.domain.exception.CargoException

sealed interface ApiResult<out T> {

    data class Success<T>(
        val data: T
    ) : ApiResult<T>

    data class Error(
        val exception: CargoException
    ) : ApiResult<Nothing>
}