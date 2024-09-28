package com.example.listadecompras.feature.shopping_lists

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecompras.R
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

        getData()

        adapter = ShoppingListAdapter(shoppingListOfList, ::onListItemClicked)
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageListActivity::class.java)
            startActivityForResult(intent, 1)
        }

        binding.searchField.addTextChangedListener{ text ->
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
                adapter.updateList(shoppingListOfList) //perguntar para o bruno o porque o notifyDataSetChanged não está funcionando

        }
    }

    private fun onListItemClicked(list_of_list: ShoppingListOfList) {
        val intent = Intent(this, ShoppingItemActivity::class.java)
        intent.putExtra("title", list_of_list.getNameList())
        intent.putExtra("items", ArrayList(list_of_list.shoppingList))
        startActivity(intent)
    }

    private fun getData() {
        val result = shoppingListViewModel.getAllLists()
        result.fold(
            onSuccess = { data -> shoppingListOfList = data.toMutableList()},
            onError = { error -> println(error.messageError()) })
    }


}
