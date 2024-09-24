package com.example.listadecompras.presentation

import Category
import ShoppingItem
import java.io.Serializable

class ShoppingListOfList(
    var id: Int,
    var name: String,
    var image: Int,
    var shoppingList: MutableList<ShoppingItem>
) : Serializable {
    public fun addItem(item: ShoppingItem) {
        shoppingList.add(item);
    }

    public fun removeItem(item: ShoppingItem) {
        shoppingList.remove(item);
    }

    public fun getItems(): MutableList<ShoppingItem> {
        return shoppingList;
    }

    public fun getNameList(): String {
        return name;
    }

    public fun getImageList(): Int {
        return image;
    }
}