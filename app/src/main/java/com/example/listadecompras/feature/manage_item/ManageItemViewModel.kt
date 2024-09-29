package com.example.listadecompras.feature.manage_item

import ShoppingItem
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.ListRepository

class ManageItemViewModel(private val listRepository: ListRepository) : ViewModel() {

    fun add(newItem: ShoppingItem, idList: Int) {
        listRepository.addItemInList(newItem, idList)
    }

    fun getNextId(idList: Int): Int {
        val response = listRepository.getAllItemsOfList(idList)
        return response.fold(
            onSuccess = { data -> data.size },
            onError = { data -> 0 }
        )
    }
}