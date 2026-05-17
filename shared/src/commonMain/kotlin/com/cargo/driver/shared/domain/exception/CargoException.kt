package com.cargo.driver.shared.domain.exception

sealed class CargoException(message: String = ""): Exception(message)

class NoInternetException : CargoException("No internet connection")
class UnauthorizedException : CargoException("Unauthorized")
class NotFoundException : CargoException("Not found")
class ServerException : CargoException("Server error")
class UnknownException : CargoException("Unknown error")