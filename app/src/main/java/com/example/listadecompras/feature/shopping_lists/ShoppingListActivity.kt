package com.example.listadecompras.feature.shopping_lists

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecompras.databinding.ActivityShoppingListBinding
import com.example.listadecompras.feature.manage_list.ManageListActivity
import com.example.listadecompras.feature.shopping_items.ShoppingItemActivity
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListActivity : ComponentActivity(), ShoppingListContracts.View {
    private lateinit var binding: ActivityShoppingListBinding
    private val shoppingListViewModel: ShoppingListViewModel by viewModel()
    private var shoppingListOfList = mutableListOf<ShoppingListOfList>()
    private lateinit var adapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        adapter = ShoppingListAdapter(shoppingListOfList, ::navigateToListView, ::onLongClick)
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageListActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.searchField.addTextChangedListener { text ->
            shoppingListViewModel.searchList(
                text.toString(),
                onSuccess = { data -> adapter.updateList(data) },
                onError = { error -> showError(error) })
        }

        binding.exitButton.setOnClickListener {
            shoppingListViewModel.logout()
            finish()
        }
    }

    private fun getData() {
        shoppingListViewModel.getAllLists(
            onSuccess = { data -> shoppingListOfList = data.toMutableList() },
            onError = { error -> showError(error) }
        )
    }

    private fun onLongClick(listOfList: ShoppingListOfList) {
        val options = arrayOf("Editar", "Excluir")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Lista: ${listOfList.getNameList()}").setItems(options) { dialog, which ->
            when (which) {
                0 -> navigateToEditList(listOfList)
                1 -> deleteItem(listOfList)
            }
        }
        builder.show()
    }

    private fun deleteItem(item: ShoppingListOfList) {
        shoppingListViewModel.removeListOfList(item.id, onSuccess = {
            getData()
            adapter.updateList(shoppingListOfList)
        }, onError = {
            showError(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getData()
            adapter.updateList(shoppingListOfList)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToEditList(list: ShoppingListOfList) {
        val intent = Intent(this, ManageListActivity::class.java)
        intent.putExtra("listId", list.getIdList())
        startActivityForResult(intent, 1)
    }

    override fun navigateToListView(listOfList: ShoppingListOfList) {
        val intent = Intent(this, ShoppingItemActivity::class.java)
        intent.putExtra("title", listOfList.getNameList())
        intent.putExtra("idList", listOfList.getIdList())
        startActivity(intent)
    }
}
