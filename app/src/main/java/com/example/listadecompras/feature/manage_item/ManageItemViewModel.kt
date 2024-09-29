package com.example.listadecompras.feature.manage_item

import ShoppingItem
import android.util.Log
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

    fun update(idList: Int, item: ShoppingItem) {
        var result = listRepository.updateItem(idList, item.id, item)
        result.fold(
            onSuccess = { data -> Log.d("Sucesso", "Item atualizado") },
            onError = { data -> Log.d("Erro", data.messageError()) }
        )
    }
}