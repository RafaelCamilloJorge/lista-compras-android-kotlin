package com.example.listadecompras.feature.shopping_lists

import com.example.listadecompras.presentation.ShoppingListOfList

class ShoppingListContracts {
    interface View {
        fun showError(message: String)
        fun navigateToEditList(list: ShoppingListOfList)
        fun navigateToListView(listOfList: ShoppingListOfList)
    }

    interface ViewModel {
        fun logout()
        fun searchList(
            name: String,
            onSuccess: (List<ShoppingListOfList>) -> Unit,
            onError: (String) -> Unit
        )

        fun removeListOfList(id: Int, onSuccess: () -> Unit, onError: (String) -> Unit)
        fun getAllLists(
            onSuccess: (List<ShoppingListOfList>) -> Unit,
            onError: (String) -> Unit
        )
    }
}