package com.example.listadecompras.feature.shopping_lists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityShoppingListBinding

class ShoppingListActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val items = listOf(
            ShoppingItem(R.drawable.ic_exit, "Lista 1"),
            ShoppingItem(R.drawable.ic_exit, "Lista 2"),
        )

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = ShoppingListAdapter(items)
    }
}
