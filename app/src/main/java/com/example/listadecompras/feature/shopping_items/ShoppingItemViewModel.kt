package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import androidx.lifecycle.ViewModel
import com.example.listadecompras.repositories.ListRepository

class ShoppingItemViewModel(private val listRepository: ListRepository) : ViewModel() {
    fun getAllItems(idList: Int): List<ShoppingItem> {
        return listRepository.getAllItemsOfList(idList)
    }
}