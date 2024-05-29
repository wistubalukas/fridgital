package org.example.fridgital.groceries.domain

object GroceryValidator {
    fun validateGrocery(grocery: Grocery): ValidationResult {
        var result = ValidationResult()

        if (grocery.title.isBlank()) {
            result = result.copy(
                tileError = "Title can't be empty"
            )
        }
        return result
    }

    data class ValidationResult(
        val tileError: String? = null
    )
}