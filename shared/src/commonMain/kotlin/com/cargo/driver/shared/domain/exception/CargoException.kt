package com.cargo.driver.shared.domain.exception

sealed class CargoException(message: String = ""): Exception(message)
// Write here all needed exception like this :
class NoInternetException(message: String = ""): CargoException(message)