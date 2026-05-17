package com.cargo.driver.shared.data.remote.util

import com.cargo.driver.shared.domain.exception.CargoException
import com.cargo.driver.shared.domain.exception.NoInternetException
import com.cargo.driver.shared.domain.exception.NotFoundException
import com.cargo.driver.shared.domain.exception.ServerException
import com.cargo.driver.shared.domain.exception.UnauthorizedException
import com.cargo.driver.shared.domain.exception.UnknownException
import com.cargo.driver.shared.domain.result.ApiResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException

suspend inline fun <reified T> safeApiCall(
    execute: suspend () -> HttpResponse
): ApiResult<T> {

    return try {

        val response = execute()

        when (response.status.value) {

            in 200..299 -> {
                ApiResult.Success(
                    response.body<T>()
                )
            }

            HttpStatusCode.Unauthorized.value -> {
                ApiResult.Error(UnauthorizedException())
            }

            HttpStatusCode.NotFound.value -> {
                ApiResult.Error(NotFoundException())
            }

            in 500..599 -> {
                ApiResult.Error(ServerException())
            }

            else -> {
                ApiResult.Error(UnknownException())
            }
        }

    } catch (e: IOException) {

        ApiResult.Error(NoInternetException())

    } catch (e: UnresolvedAddressException) {

        ApiResult.Error(NoInternetException())

    } catch (e: CargoException) {

        ApiResult.Error(e)

    } catch (e: Exception) {

        ApiResult.Error(UnknownException())
    }
}