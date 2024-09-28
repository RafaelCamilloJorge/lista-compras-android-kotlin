package com.example.listadecompras.feature.shopping_items

import Category
import ShoppingItem
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.ActivityShoppingItemBinding
import com.example.listadecompras.feature.manage_item.ManageItemActivity
import com.example.listadecompras.feature.shopping_lists.ShoppingListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingItemActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingItemBinding
    private val shoppingItemViewModel: ShoppingItemViewModel by viewModel()
    private var items = mutableListOf<ShoppingItem>()
    private lateinit var adapter: ShoppingItemAdapter
    private var idList: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idList = intent.getIntExtra("idList", 0)
        val title = intent.getStringExtra("title")

        items = getData(idList).toMutableList()

        binding.textView.text = title

        adapter = ShoppingItemAdapter(items, ::onListItemClicked)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageItemActivity::class.java)
            intent.putExtra("idList", idList)

            startActivityForResult(intent, 1)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getData(idList).let {
                items.clear()
                items.addAll(it)
                adapter.updateList(items)
            }

        }
    }

    private fun onListItemClicked(item: ShoppingItem) {
        Toast.makeText(this, "Clicou em: ${item.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getData(idList: Int): List<ShoppingItem> {
        return shoppingItemViewModel.getAllItems(idList)
    }
}

