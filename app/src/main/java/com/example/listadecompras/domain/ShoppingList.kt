package com.example.listadecompras.presentation

import Category
import ShoppingItem

class ShoppingList(
    var name: String,
    var image: String,
    var category: Category,
    var shoppingList: MutableList<ShoppingItem> = mutableListOf()
) {
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

    public fun getImageList(): String {
        return image;
    }

    public fun getCategoryList(): Category {
        return category;
    }
}