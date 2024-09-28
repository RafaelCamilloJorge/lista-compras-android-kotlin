package com.example.listadecompras.feature.manage_item

import Category
import ShoppingItem
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityManageItemBinding
import com.example.listadecompras.databinding.ActivityManageListBinding
import com.example.listadecompras.feature.manage_list.ManageListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageItemBinding
    private val manageItemViewModel: ManageItemViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idList = intent.getIntExtra("idList", 0)

        binding.saveButton.setOnClickListener {
            saveItem(idList)
            val resultIntent = Intent()
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }

    fun saveItem(idList: Int) {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString().toInt()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()
        val item = ShoppingItem(
            id = manageItemViewModel.getNextId(idList) + 1,
            name = itemName.first().uppercase() + itemName.substring(1),
            image = Category.valueOf(itemCategory).getIcon(),
            quantity = itemQuantity,
            unity = UnitOfMeasure.valueOf(itemUnit),
            category = Category.valueOf(itemCategory)
        )
        manageItemViewModel.add(item, idList)
    }
}