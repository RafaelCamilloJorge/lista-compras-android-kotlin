package com.example.listadecompras.feature.manage_item

import ShoppingItem
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.ListRepository

class ManageItemViewModel(private val listRepository: ListRepository) : ViewModel(),
    ManageItemContracts.ViewModel {

    override fun add(
        newItem: ShoppingItem,
        idList: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val result = listRepository.addItemInList(newItem, idList)
        result.fold(
            onSuccess = { onSuccess() },
            onError = { onError(it.messageError()) }
        )
    }

    override fun update(
        idList: Int,
        idItem: Int,
        item: ShoppingItem,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val result = listRepository.updateItem(idList, idItem, item)
        result.fold(
            onSuccess = { onSuccess() },
            onError = { onError(it.messageError()) }
        )
    }

    override fun getItemById(
        idList: Int,
        idItem: Int,
        onSuccess: (ShoppingItem) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = listRepository.getItemById(idList, idItem)
        return response.fold(
            onSuccess = { onSuccess(it) },
            onError = { onError(it.messageError()) }
        )
    }

    override fun getNextId(idList: Int, onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
        val response = listRepository.getAllItemsOfList(idList)
        return response.fold(
            onSuccess = { onSuccess(it.size + 1) },
            onError = { onError(it.messageError()) }
        )
    }
}
