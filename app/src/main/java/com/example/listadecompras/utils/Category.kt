package com.example.listadecompras.utils

import com.example.listadecompras.R

enum class Category {
    vegetables,
    meat,
    dairy,
    fish,
    seeds;

    fun getIcon(): Int {
        return when (this) {
            vegetables -> R.drawable.ic_vegetables
            meat -> R.drawable.ic_meat
            dairy -> R.drawable.ic_dairy
            fish -> R.drawable.ic_fish
            seeds -> R.drawable.ic_seeds
        }
    }

    fun getName(): String {
        return when (this) {
            vegetables -> "Vegetais"
            fish -> "Peixes"
            meat -> "Carnes"
            dairy -> "Laticínio"
            seeds -> "Grãos"
        }
    }
}