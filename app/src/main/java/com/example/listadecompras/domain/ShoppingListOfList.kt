package com.example.listadecompras.presentation

import ShoppingItem
import java.io.Serializable

class ShoppingListOfList(
    var id: Int,
    var name: String,
    var image: String?,
    var shoppingList: MutableList<ShoppingItem>
) {
    public fun addItem(item: ShoppingItem) {
        shoppingList.add(item)
    }

    public fun removeItem(item: ShoppingItem) {
        shoppingList.remove(item)
    }

    public fun getItems(): MutableList<ShoppingItem> {
        return shoppingList
    }

    public fun getNameList(): String {
        return name
    }

    public fun getIdList(): Int {
        return id
    }

    fun getImageList(): String? {
        return image
    }
}