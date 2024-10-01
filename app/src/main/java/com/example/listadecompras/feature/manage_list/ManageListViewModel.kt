package com.example.listadecompras.feature.manage_list

import androidx.lifecycle.ViewModel
import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.ListRepository

class ManageListViewModel(
    private val listRepository: ListRepository
) : ViewModel(), ManageListContracts.ViewModel {
    override fun add(
        newList: ShoppingListOfList,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.createShoppingListOfList(newList)
        response.fold(
            onSuccess = { onSuccess() },
            onError = { data -> onError(data.messageError()) })
    }

    override fun getById(
        id: Int,
        onSuccess: (ShoppingListOfList) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.getListsOfListById(id)
        return response.fold(
            onSuccess = { data -> onSuccess(data) },
            onError = { data -> onError(data.messageError()) }
        )
    }

    override fun update(
        id: Int,
        newList: ShoppingListOfList,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.updateShoppingList(id, newList)
        response.fold(
            onSuccess = { onSuccess() },
            onError = { data -> onError(data.messageError()) })
    }

    override fun getNextId(onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
        val response = listRepository.getAllListOfLists()
        return response.fold(
            onSuccess = { data -> onSuccess(data.size + 1) },
            onError = { data -> onError(data.messageError()) }
        )
    }

}