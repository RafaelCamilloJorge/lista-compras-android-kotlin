package com.example.listadecompras.presentation

import ShoppingItem
import android.net.Uri
import java.io.Serializable

class ShoppingListOfList(
    var id: Int,
    var name: String,
    var image: String?,
    var shoppingList: MutableList<ShoppingItem>
) : Serializable {
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

    fun getImageList(): Uri? {
        return image?.let { Uri.parse(it) }
    }
}