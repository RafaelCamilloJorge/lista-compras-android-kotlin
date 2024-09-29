package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.ActivityShoppingItemBinding
import com.example.listadecompras.feature.manage_item.ManageItemActivity
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

        getDataIntent()

        adapter = ShoppingItemAdapter(items, ::onLongClick)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageItemActivity::class.java)
            intent.putExtra("idListListOfList", idList)
            startActivityForResult(intent, 1)
        }

        binding.searchField.addTextChangedListener { text ->
            val searchText = text.toString()
            adapter.search(searchText)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            updateList()
        }
    }

    private fun onLongClick(item: ShoppingItem) {
        val options = arrayOf("Editar", "Excluir")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Item: ${item.name}")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> editItem(item)
                    1 -> deleteItem(item)
                }
            }
        builder.show()
    }

    private fun editItem(item: ShoppingItem) {
        val intent = Intent(this, ManageItemActivity::class.java)
        intent.putExtra("listData", item)
        intent.putExtra("idListListOfList", idList)
        startActivityForResult(intent, 1)
    }

    private fun deleteItem(item: ShoppingItem) {
        shoppingItemViewModel.deleteItem(idList, item.id)
        updateList()
    }

    private fun getData(idList: Int): List<ShoppingItem> {
        return shoppingItemViewModel.getAllItems(idList)
    }

    private fun updateList() {
        getData(idList).let {
            items.clear()
            items.addAll(it)
            adapter.updateList()
        }
    }

    private fun getDataIntent() {
        idList = intent.getIntExtra("idList", 1)
        val title = intent.getStringExtra("title")

        items = getData(idList).toMutableList()

        binding.textView.text = title
    }
}

