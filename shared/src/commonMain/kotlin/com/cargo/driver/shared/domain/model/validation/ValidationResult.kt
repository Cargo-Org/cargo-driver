package com.cargo.driver.shared.domain.model.validation

sealed interface ValidationResult {
    object Empty : ValidationResult
    object Invalid : ValidationResult
    object Valid : ValidationResult
}