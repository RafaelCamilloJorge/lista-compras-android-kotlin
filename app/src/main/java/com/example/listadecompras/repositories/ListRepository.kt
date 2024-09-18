package com.example.listadecompras.repositories

import CustomError
import OnResult
import ShoppingItem
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listadecompras.presentation.ShoppingListOfList

class ListRepository {
    private var shoppingListOfLists = mutableListOf<ShoppingListOfList>()

    fun createShoppingListOfList(shoppingListOfList: ShoppingListOfList): OnResult<Nothing> {
        try {
            shoppingListOfLists.add(shoppingListOfList)
            return OnResult.Success(null) as Nothing
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao criar a lista"))
        }
    }

    fun getShoppingListById(id: Int): ShoppingListOfList? {
        shoppingListOfLists.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun removeShoppingListById(id: Int): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    shoppingListOfLists.remove(it)
                    return OnResult.Success(null) as Nothing;
                }
            }
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao deletar lista"))
        }
        return OnResult.Error(CustomError("Lista não encontrada"))
    }

    fun updateShoppingList(id: Int, newList: ShoppingListOfList): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    val index: Int = shoppingListOfLists.indexOf(it)
                    shoppingListOfLists[index] = newList
                }
            }
            return OnResult.Success(null) as Nothing;
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao atualizar a lista"))
        }
    }

    fun getListsOfList(id: Int): OnResult<List<ShoppingListOfList>> {
        val list: MutableList<ShoppingListOfList> = mutableListOf()
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    list.add(it)
                }
            }
            return OnResult.Success(list)
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao buscar a lista"))
        }

    }

    fun addItemInList(id: Int, item: ShoppingItem) {
//        itemList.add(item)
    }

    fun removeItemById(idShoppingItem: Int, idItem: Int) {
//        itemList.forEach {
//            if (it.id == id) {
//                itemList.remove(it)
//                return
//            }
//        }
    }

    fun updateItem(id: Int, newItem: ShoppingItem) {
//        itemList.forEach {
//            if (it.id == id) {
//                val index: Int = itemList.indexOf(it)
//                itemList[index] = newItem
//                return
//            }
//        }
    }
}