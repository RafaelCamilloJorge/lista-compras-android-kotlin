package com.example.listadecompras.feature.shopping_items

import ShoppingItem
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityShoppingItemBinding

class ShoppingItemActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list_of_items = mutableListOf(
            ShoppingItem(1, "Alface", 1, R.drawable.ic_exit, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(2, "Brócolis", 1, R.drawable.ic_exit, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(2, "Abobora", 1, R.drawable.ic_exit, UnitOfMeasure.kilo, Category.vegetables)
        )


        binding.recyclerView.adapter = ShoppingItemAdapter(list_of_items) { item ->
            onListItemClicked(item)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onListItemClicked(item: ShoppingItem) {
        Toast.makeText(this, "Clicou em: ${item.name}", Toast.LENGTH_SHORT).show()
    }
}
