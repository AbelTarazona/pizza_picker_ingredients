package com.proyecto.pizzaingredient

import com.proyecto.pizzaingredient.R.*


/**
 * Created by AbelTarazona on 16/01/2021
 */

data class Size(
    val size: String,
    val scale: Float,
    val price: Int
)

data class Ingredient(
    val img: Int,
    var isSelected: Boolean = false
)

fun getSizes() = listOf(
    Size(size = "S", scale = 0.8f, price = 12),
    Size(size = "M", scale = 1f, price = 18),
    Size(size = "L", scale = 1.2f, price = 25),
)

fun getIngredients() = listOf(
    Ingredient(img = drawable.potato),
    Ingredient(img = drawable.chili),
    Ingredient(img = drawable.garlic),
    Ingredient(img = drawable.olive),
    Ingredient(img = drawable.onion),
    Ingredient(img = drawable.pea),
    Ingredient(img = drawable.pickle)
)

val ingredientsList = listOf(
    drawable.potato,
    drawable.chili,
    drawable.garlic,
    drawable.olive,
    drawable.onion,
    drawable.pea,
    drawable.pickle,
)