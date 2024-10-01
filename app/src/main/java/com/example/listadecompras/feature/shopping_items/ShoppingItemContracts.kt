package com.example.listadecompras.feature.shopping_items

import ShoppingItem

class ShoppingItemContracts {
    interface View {
        fun showError(message: String)
        fun goBack()
    }

    interface ViewModel {
        fun getAllItems(
            idList: Int,
            onSuccess: (List<ShoppingItem>) -> Unit,
            onError: (String) -> Unit
        )

        fun deleteItem(idList: Int, idItem: Int, onSuccess: () -> Unit, onError: (String) -> Unit)
    }

}