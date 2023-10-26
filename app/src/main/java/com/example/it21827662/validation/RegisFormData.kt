package com.example.it21827662.validation

import com.example.it21827662.validation.regisValidation.RegisValidationResult

class RegisFormData(
    private var username: String,
    private var gender: String,
    private var email: String,
    private var password: String
) {
    fun validateUsername(): RegisValidationResult {
        return if (username.isEmpty()) {
            RegisValidationResult.Empty("Name is empty")
        } else {
            RegisValidationResult.Valid
        }
    }

    fun validateGender(): RegisValidationResult {
        return if (gender.isEmpty()) {
            RegisValidationResult.Empty("Province is empty")
        } else {
            RegisValidationResult.Valid
        }
    }

    fun validateEmail(): RegisValidationResult {
        return if (email.isEmpty()) {
            RegisValidationResult.Empty("Email is empty")
        } else if (!email.endsWith("@gmail.com")) {
            RegisValidationResult.Invalid("Email must end with @gmail.com")
        } else {
            RegisValidationResult.Valid
        }
    }

    fun validatePassword(): RegisValidationResult {
        return if (password.isEmpty()) {
            RegisValidationResult.Empty("Password is empty")
        } else {
            RegisValidationResult.Valid
        }
    }
}