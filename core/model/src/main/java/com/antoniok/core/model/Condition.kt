package com.antoniok.core.model

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

val dummyCondition = Condition(
    text = "Rainy",
    icon = "www.image.com",
    code = 123
)
