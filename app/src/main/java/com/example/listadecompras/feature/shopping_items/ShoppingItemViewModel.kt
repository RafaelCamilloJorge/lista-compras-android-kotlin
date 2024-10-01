package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.ListRepository

class ShoppingItemViewModel(private val listRepository: ListRepository) : ViewModel(),
    ShoppingItemContracts.ViewModel {
    override fun getAllItems(
        idList: Int,
        onSuccess: (List<ShoppingItem>) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.getAllItemsOfList(idList)
        return response.fold(
            onSuccess = { data -> onSuccess(data) },
            onError = { data -> onError(data.messageError()) }
        )
    }

    override fun deleteItem(
        idList: Int,
        idItem: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.removeItemById(idList, idItem)
        return response.fold(
            onSuccess = { onSuccess() },
            onError = { data -> onError(data.messageError()) }
        )
    }
}