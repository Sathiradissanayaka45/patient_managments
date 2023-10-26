package com.example.it21827662.validation.regisValidation

sealed interface RegisValidationResult {
    data class Empty(val errorMessage: String) : RegisValidationResult
    data class Invalid(val errorMessage: String) : RegisValidationResult
    object Valid : RegisValidationResult
}