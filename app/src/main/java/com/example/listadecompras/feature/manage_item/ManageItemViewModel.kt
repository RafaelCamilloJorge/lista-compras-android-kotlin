package com.example.listadecompras.feature.manage_item

import ShoppingItem
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.ListRepository

class ManageItemViewModel(private val listRepository: ListRepository) : ViewModel() {

    fun add(newItem: ShoppingItem, idList: Int) {
        val response = listRepository.addItemInList(newItem, idList)
        return response
    }

    fun getNextId(idList: Int): Int {
        val response = listRepository.getAllItemsOfList(idList)
        return response.size
    }
}