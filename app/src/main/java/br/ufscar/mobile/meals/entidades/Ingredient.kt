package br.ufscar.mobile.meals.entidades

import java.io.Serializable

data class Ingredient(
    val measure: String,
    val ingredient: String
) : Serializable