package org.example.fridgital.groceries.data

import database.GroceryEntity
import org.example.fridgital.core.data.ImageStorage
import org.example.fridgital.groceries.domain.Grocery

suspend fun GroceryEntity.toGrocery(imageStorage: ImageStorage): Grocery {
    return Grocery(
        id = id,
        title = title,
        description = description,
        count = count,
        photoBytes = imagePath?.let {imageStorage.getImage(it)}
    )
}