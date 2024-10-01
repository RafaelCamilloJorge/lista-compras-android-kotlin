package com.example.listadecompras.feature.manage_list

import com.example.listadecompras.presentation.ShoppingListOfList

class ManageListContracts {
    interface View {
        fun showError(message: String)
        fun goBack()
    }

    interface ViewModel {
        fun add(newList: ShoppingListOfList, onSuccess: () -> Unit, onError: (String) -> Unit)
        fun getById(id: Int, onSuccess: (ShoppingListOfList) -> Unit, onError: (String) -> Unit)
        fun update(
            id: Int,
            newList: ShoppingListOfList,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        )

        fun getNextId(onSuccess: (Int) -> Unit, onError: (String) -> Unit)
    }
}