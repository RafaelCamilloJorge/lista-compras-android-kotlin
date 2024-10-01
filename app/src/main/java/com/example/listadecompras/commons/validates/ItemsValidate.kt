package com.example.listadecompras.commons.validates

import Category
import UnitOfMeasure

class ItemsValidate {
    fun validateFieldsItem(
        name: String,
        quantity: String,
        categoryEnum: Category?,
        unitEnum: UnitOfMeasure?
    ): String? {
        if (name.isEmpty()) {
            return "Por favor, preencha o nome"
        } else if (quantity.isEmpty()) {
            return "Por favor, preencha a quantidade"
        } else if(quantity == "0"){
            return "A quantidade não pode ser 0"
        } else if (categoryEnum == null) {
            return "Categoria inválida"
        } else if (unitEnum == null) {
            return "Unidade inválida"
        }
        return null
    }
}