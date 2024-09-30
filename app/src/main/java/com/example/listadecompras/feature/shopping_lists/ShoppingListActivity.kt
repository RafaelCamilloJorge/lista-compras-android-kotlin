package com.example.listadecompras.feature.shopping_lists

import Category
import ShoppingItem
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecompras.databinding.ActivityShoppingListBinding
import com.example.listadecompras.feature.login.MainActivity
import com.example.listadecompras.feature.manage_list.ManageListActivity
import com.example.listadecompras.feature.shopping_items.ShoppingItemActivity
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    private val shoppingListViewModel: ShoppingListViewModel by viewModel()
    private var shoppingListOfList = mutableListOf<ShoppingListOfList>()
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mock()

        getData()

        adapter = ShoppingListAdapter(shoppingListOfList, ::onListItemClicked, ::onLongClick)
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageListActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.searchField.addTextChangedListener { text ->
            val searchText = text.toString()
            adapter.search(searchText)
        }

        binding.exitButton.setOnClickListener {
            shoppingListViewModel.logout()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getData()
            adapter.updateList(shoppingListOfList)
        }
    }

    private fun getData() {
        val result = shoppingListViewModel.getAllLists()
        result.fold(
            onSuccess = { data -> shoppingListOfList = data.toMutableList() },
            onError = { error -> println(error.messageError()) })
    }

    private fun mock() {

    }

    private fun onListItemClicked(listOfList: ShoppingListOfList) {
        val intent = Intent(this, ShoppingItemActivity::class.java)
        intent.putExtra("title", listOfList.getNameList())
        intent.putExtra("idList", listOfList.getIdList())
        startActivity(intent)
    }

    private fun onLongClick(listOfList: ShoppingListOfList) {
        val options = arrayOf("Editar", "Excluir")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Lista: ${listOfList.getNameList()}")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> editItem(listOfList)
                    1 -> deleteItem(listOfList)
                }
            }
        builder.show()
    }

    private fun editItem(list: ShoppingListOfList) {
        val intent = Intent(this, ManageListActivity::class.java)
        intent.putExtra("listId", list.getIdList())
        startActivityForResult(intent, 1)
    }

    private fun deleteItem(item: ShoppingListOfList) {
        shoppingListViewModel.removeListOfList(item.id)
        getData()
        adapter.updateList(shoppingListOfList)
    }
}
