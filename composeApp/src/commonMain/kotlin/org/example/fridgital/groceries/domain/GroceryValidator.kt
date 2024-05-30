package org.example.fridgital.groceries.domain

object GroceryValidator {
    fun validateGrocery(grocery: Grocery): ValidationResult {
        var result = ValidationResult()

        if (grocery.title.isBlank()) {
            result = result.copy(
                tileError = "Titel zum speichern erforderlich."
            )
        }
        return result
    }

    data class ValidationResult(
        val tileError: String? = null
    )
}