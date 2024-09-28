package com.example.listadecompras.repositories

import CustomError
import OnResult
import ShoppingItem
import com.example.listadecompras.presentation.ShoppingListOfList
import com.example.listadecompras.repositories.interfaces.IListRepository

class ListRepository : IListRepository {
    private var shoppingListOfLists = mutableListOf<ShoppingListOfList>()

    //////////////////lista de listas//////////////////
    override fun createShoppingListOfList(shoppingListOfList: ShoppingListOfList): OnResult<Nothing> {
        try {
            shoppingListOfLists.add(shoppingListOfList)
            return OnResult.Success(null) as Nothing
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao criar a lista"))
        }
    }

    override fun getAllListOfLists(): OnResult<List<ShoppingListOfList>> {
        try {
            return OnResult.Success(shoppingListOfLists)
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao pegar suas listas"))
        }
    }

    override fun getListsOfListByName(name: String): OnResult<List<ShoppingListOfList>> {
        var list = mutableListOf<ShoppingListOfList>()
        try {
            shoppingListOfLists.forEach {
                if (it.name.contains(name)) {
                    list.add(it)
                }
            }
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao realizar a busca"))
        }
        return OnResult.Success(list)
    }

    override fun getListsOfListById(id: Int): OnResult<ShoppingListOfList> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    return OnResult.Success(it)
                }
            }
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao realizar a busca"))
        }
        return OnResult.Error(CustomError("Lista não encontrada"))
    }

    override fun removeListOfListById(id: Int): OnResult<Nothing> {
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

    override fun updateShoppingList(id: Int, newList: ShoppingListOfList): OnResult<Nothing> {
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

    //////////////////somente itens//////////////////
    override fun addItemInList(item: ShoppingItem, idList: Int) {
        shoppingListOfLists.forEach {
            if (it.id == idList) {
                it.shoppingList.add(item)
                return
            }
        }
    }

    override fun getAllItemsOfList(idList: Int): List<ShoppingItem> {
        shoppingListOfLists.forEach {
            if (it.id == idList) {
                return it.shoppingList
            }
        }
        return emptyList()
    }

    override fun removeItemById(idShoppingItem: Int, idItem: Int) {
        TODO("Not yet implemented")
    }

    override fun updateItem(id: Int, newItem: ShoppingItem) {
        TODO("Not yet implemented")
    }
}