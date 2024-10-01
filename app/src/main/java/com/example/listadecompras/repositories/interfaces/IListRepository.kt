package com.example.listadecompras.repositories.interfaces

import OnResult
import ShoppingItem
import com.example.listadecompras.presentation.ShoppingListOfList

interface IListRepository {
    fun createShoppingListOfList(shoppingListOfList: ShoppingListOfList): OnResult<Boolean>

    fun getAllListOfLists(): OnResult<List<ShoppingListOfList>>

    fun removeListOfListById(id: Int): OnResult<Boolean>

    fun updateShoppingList(id: Int, newList: ShoppingListOfList): OnResult<Boolean>

    fun getListsOfListByName(name: String): OnResult<List<ShoppingListOfList>>

    fun getListsOfListById(id: Int): OnResult<ShoppingListOfList>

    fun addItemInList(item: ShoppingItem, idList: Int): OnResult<Boolean>

    fun getAllItemsOfList(idList: Int): OnResult<List<ShoppingItem>>

    fun getItemById(idList: Int, idItem: Int): OnResult<ShoppingItem>

    fun removeItemById(idShoppingItem: Int, idItem: Int): OnResult<Boolean>

    fun updateItem(idList: Int, idItem: Int, newItem: ShoppingItem): OnResult<Boolean>
}