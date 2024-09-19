package com.example.listadecompras.feature.shopping_lists

import ShoppingItem
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityShoppingListBinding
import com.example.listadecompras.presentation.ShoppingListOfList

class ShoppingListActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list_of_items = mutableListOf(
            ShoppingItem(1, "Alface", 1, UnitOfMeasure.kilo, Category.vegetables),
            ShoppingItem(2, "Brócolis", 1, UnitOfMeasure.kilo, Category.vegetables)
        )


        val list_of_list = mutableListOf(
            ShoppingListOfList(1, "Saudavel", R.drawable.ic_exit, list_of_items ),
            ShoppingListOfList(2, "Fim de Semana", R.drawable.ic_exit, list_of_items )
        )

        val adapter = ShoppingListAdapter(list_of_list, ::onListItemClicked)
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

    }

    private fun onListItemClicked(list_of_list : ShoppingListOfList) {
        Toast.makeText(this, "Clicou no " + list_of_list.name, Toast.LENGTH_LONG).show()
    }
}
