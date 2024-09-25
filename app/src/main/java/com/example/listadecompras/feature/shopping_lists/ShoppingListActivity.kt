package com.example.listadecompras.feature.shopping_lists

import Category
import ShoppingItem
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityShoppingListBinding
import com.example.listadecompras.feature.login.LoginViewModel
import com.example.listadecompras.feature.manage_list.ManageListActivity
import com.example.listadecompras.feature.shopping_items.ShoppingItemActivity
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingListActivity : ComponentActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    private val shoppingListViewModel: ShoppingListViewModel by viewModel()
    private var shoppingListOfList = listOf<ShoppingListOfList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createMockdata()
        getData()

        val adapter = ShoppingListAdapter(shoppingListOfList, ::onListItemClicked)
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager

        binding.fab.setOnClickListener {
            val intent = Intent(this, ManageListActivity::class.java)
            startActivity(intent)
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
            onSuccess = { data -> shoppingListOfList = data },
            onError = { error -> println(error.messageError()) })
    }

    private fun createMockdata() {
        val list_of_itemsSaudavel = mutableListOf<ShoppingItem>(
            ShoppingItem(
                1,
                "Alface",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.kilo,
                Category.vegetables
            ),
            ShoppingItem(
                2,
                "Brócolis",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.kilo,
                Category.vegetables
            )
        )

        val list_of_itemsFimDeSemana = mutableListOf(
            ShoppingItem(
                1,
                "Chocolate",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.kilo,
                Category.vegetables
            ),
            ShoppingItem(
                2,
                "Pipoca",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.kilo,
                Category.vegetables
            )
        )

        val list_of_itemsFeira = mutableListOf(
            ShoppingItem(
                1,
                "Pastel",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.kilo,
                Category.fish
            ),
            ShoppingItem(
                2,
                "Caldo de Cana",
                Category.vegetables.getIcon(),
                1,
                UnitOfMeasure.liter,
                Category.dairy
            )
        )

        shoppingListViewModel.add(
            ShoppingListOfList(
                0,
                "Teste save",
                R.drawable.ic_exit,
                list_of_itemsSaudavel
            )
        )

        shoppingListViewModel.add(
            ShoppingListOfList(
                1,
                "Saudavel",
                R.drawable.ic_exit,
                list_of_itemsSaudavel
            )
        )
        shoppingListViewModel.add(
            ShoppingListOfList(
                2,
                "Fim de Semana",
                R.drawable.ic_exit,
                list_of_itemsFimDeSemana
            ),
        )
        shoppingListViewModel.add(
            ShoppingListOfList(
                3,
                "Feira",
                R.drawable.ic_exit,
                list_of_itemsFeira
            )
        )

    }
}
