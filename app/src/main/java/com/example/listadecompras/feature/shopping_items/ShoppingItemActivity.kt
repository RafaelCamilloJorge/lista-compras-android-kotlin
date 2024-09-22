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

        var title = intent.getStringExtra("title")
        binding.textView.text = title

        val list_of_items = mutableListOf(
            ShoppingItem(1, "Cenoura", Category.vegetables.getIcon(), 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(2, "Carne", Category.meat.getIcon(), 2, UnitOfMeasure.kilo, Category.meat),
            ShoppingItem(3, "Arroz", Category.seeds.getIcon(), 10, UnitOfMeasure.gram, Category.seeds),
            ShoppingItem(4, "Leite", Category.dairy.getIcon(), 5, UnitOfMeasure.liter, Category.dairy),
            ShoppingItem(5, "Peixe", Category.fish.getIcon(), 3, UnitOfMeasure.kilo, Category.fish),
            ShoppingItem(1, "Brocolis", Category.vegetables.getIcon(), 1, UnitOfMeasure.kilo, Category.vegetables),
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
