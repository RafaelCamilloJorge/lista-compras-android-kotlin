package com.example.listadecompras.feature.shopping_items

import Category
import ShoppingItem
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.ActivityShoppingItemBinding

class ShoppingItemActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")

        val items = intent.getSerializableExtra("items") as? ArrayList<ShoppingItem>

        binding.textView.text = title

        val adapter = ShoppingItemAdapter(items ?: emptyList()) { item ->
            Toast.makeText(this, "Clicou no item ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun onListItemClicked(item: ShoppingItem) {
        Toast.makeText(this, "Clicou em: ${item.name}", Toast.LENGTH_SHORT).show()
    }
}
