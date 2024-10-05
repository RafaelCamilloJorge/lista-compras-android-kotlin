package com.example.listadecompras.feature.shopping_lists

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.ListRepository

class ShoppingListViewModel(private val listRepository: ListRepository) : ViewModel(),
    ShoppingListContracts.ViewModel {

    override fun getAllLists(
        onSuccess: (List<ShoppingListOfList>) -> Unit,
        onError: (String) -> Unit
    ) {
        val result = listRepository.getAllListOfLists()
        result.fold(
            onSuccess = { onSuccess(it) },
            onError = {
                onError(it.messageError())
            })
    }

    override fun logout() {
        listRepository.removeAllItemsOfList()
    }

    override fun searchList(
        name: String,
        onSuccess: (List<ShoppingListOfList>) -> Unit,
        onError: (String) -> Unit
    ) {
        val result = listRepository.getListsOfListByName(name)
        result.fold(
            onSuccess = { onSuccess(it) },
            onError = {
                onError(it.messageError())
            }
        )
    }

    override fun removeListOfList(id: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val result = listRepository.removeListOfListById(id)
        result.fold(
            onSuccess = { onSuccess() },
            onError = {
                onError(it.messageError())
            })
    }
}