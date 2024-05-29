package org.example.fridgital.groceries.domain

data class Grocery(
    val id: Long?,
    val title: String,
    val description: String,
    val count: Long,
    val photoBytes: ByteArray?
)
