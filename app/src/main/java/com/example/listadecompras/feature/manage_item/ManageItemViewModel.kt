package com.example.listadecompras.feature.manage_item

import ShoppingItem

class ManageItemViewModel {
    private var itemList = mutableListOf<ShoppingItem>()

    fun addItemInList(item: ShoppingItem) {
        itemList.add(item)
    }

    fun removeItemById(id: Int) {
        itemList.forEach {
            if (it.id == id) {
                itemList.remove(it)
                return
            }
        }
    }

    fun updateItem(id: Int, newItem: ShoppingItem) {
        itemList.forEach {
            if (it.id == id) {
                val index: Int = itemList.indexOf(it)
                itemList[index] = newItem
                return
            }
        }
    }
}