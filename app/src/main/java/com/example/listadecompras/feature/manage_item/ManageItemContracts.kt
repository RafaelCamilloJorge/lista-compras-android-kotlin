package com.example.listadecompras.feature.manage_item

import ShoppingItem

class ManageItemContracts {
    interface View {
        fun showError(message: String)
        fun goBack()
    }

    interface ViewModel {
        fun add(
            newItem: ShoppingItem,
            idList: Int,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        )

        fun update(
            idList: Int,
            idItem: Int,
            item: ShoppingItem,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        )

        fun getItemById(
            idList: Int,
            idItem: Int,
            onSuccess: (ShoppingItem) -> Unit,
            onError: (String) -> Unit
        )

        fun getNextId(idList: Int, onSuccess: (Int) -> Unit, onError: (String) -> Unit)
    }
}