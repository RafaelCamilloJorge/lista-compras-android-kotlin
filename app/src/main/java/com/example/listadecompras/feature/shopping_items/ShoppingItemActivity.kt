package com.example.listadecompras.feature.shopping_items

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

        var title = intent.getStringExtra("title")
        binding.textView.text = title

        val list_of_items = mutableListOf(
            ShoppingItem(1, "Alface", 1, 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(2, "Brócolis", 1, 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(3, "Abobora", 1, 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(4, "Arroz", 1, 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(5, "Feijão", 1, 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(6, "Batata", 1, 1, UnitOfMeasure.kilo, Category.vegetables)
        )


        val adapter = ShoppingItemAdapter(list_of_items) { item ->
            Toast.makeText(this, "Clicou no item ${item.name}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun onListItemClicked(item: ShoppingItem) {
        Toast.makeText(this, "Clicou em: ${item.name}", Toast.LENGTH_SHORT).show()
    }
}
