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
            return OnResult.Success(list)
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao realizar a busca"))
        }
    }

    override fun getListsOfListById(id: Int): OnResult<ShoppingListOfList> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    return OnResult.Success(it)
                }
            }
            return OnResult.Error(CustomError("Lista não encontrada"))
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao realizar a busca"))
        }
    }

    override fun removeListOfListById(id: Int): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    shoppingListOfLists.remove(it)
                    return OnResult.Success(null) as Nothing;
                }
            }
            return OnResult.Error(CustomError("Lista não encontrada"))
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao deletar lista"))
        }
    }

    override fun updateShoppingList(id: Int, newList: ShoppingListOfList): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == id) {
                    val index: Int = shoppingListOfLists.indexOf(it)
                    shoppingListOfLists[index] = newList
                    return OnResult.Success(null) as Nothing
                }
            }
            return OnResult.Success(null) as Nothing
        } catch (e: Exception) {
            return OnResult.Error(CustomError("Erro ao atualizar a lista"))
        }
    }

    //////////////////somente itens//////////////////
    override fun addItemInList(item: ShoppingItem, idList: Int): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == idList) {
                    it.shoppingList.add(item)
                    return OnResult.Success(null) as Nothing
                }
            }
            return OnResult.Error(CustomError("Erro ao adicionar o item"))
        } catch (error: Exception) {
            return OnResult.Error(CustomError("Erro ao adicionar o item"))
        }
    }

    override fun getAllItemsOfList(idList: Int): OnResult<List<ShoppingItem>> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == idList) {
                    return OnResult.Success(it.shoppingList)
                }
            }
            return OnResult.Success(emptyList())
        } catch (error: Exception) {
            return OnResult.Error(CustomError("Erro ao buscar os dados da lista"))
        }

    }

    override fun removeItemById(idList: Int, idItem: Int): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == idList) {
                    for (item in it.shoppingList) {
                        if (item.id == idItem) {
                            it.shoppingList.remove(item)
                            return OnResult.Success(null) as Nothing
                        }
                    }
                }
            }
            return OnResult.Error(CustomError("Item não encontrado"))
        } catch (error: Exception) {
            return OnResult.Error(CustomError("Erro ao remover o item"))
        }
    }

    override fun updateItem(idList: Int, idItem: Int, newItem: ShoppingItem): OnResult<Nothing> {
        try {
            shoppingListOfLists.forEach {
                if (it.id == idList) {
                    for (item in it.shoppingList) {
                        if (item.id == idItem) {
                            it.shoppingList[it.shoppingList.indexOf(item)] = newItem
                            return OnResult.Success(null) as Nothing
                        }
                    }
                }
            }
            return OnResult.Error(CustomError("Item não encontrado"))
        } catch (error: Exception) {
            return OnResult.Error(CustomError("Erro ao atualizar o item"))
        }
    }
}