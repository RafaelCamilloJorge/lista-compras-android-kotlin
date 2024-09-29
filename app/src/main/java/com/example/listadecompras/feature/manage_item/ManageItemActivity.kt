package com.example.listadecompras.feature.manage_item

import Category
import ShoppingItem
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityManageItemBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageItemBinding
    private val manageItemViewModel: ManageItemViewModel by viewModel()
    private var shoppingItem: ShoppingItem? = null
    private var currentIdList: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataIntentForEditItem()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.saveButton.setOnClickListener {
            if (validateFields() && currentIdList != null) {
                if (shoppingItem != null) updateItem() else saveItem()
            }
        }
    }

    private fun updateItem() {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString().toInt()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()

        val categoryEnum = Category.values().firstOrNull { it.getName() == itemCategory }
        val unitEnum = UnitOfMeasure.values().firstOrNull { it.getName() == itemUnit }

        if (categoryEnum == null || unitEnum == null) {
            Toast.makeText(this, "Erro: Categoria ou unidade inválida.", Toast.LENGTH_SHORT).show()
        } else {
            val item = ShoppingItem(
                id = shoppingItem!!.id,
                name = itemName.first().uppercase() + itemName.substring(1),
                image = categoryEnum.getIcon(),
                quantity = itemQuantity,
                unity = unitEnum,
                category = categoryEnum
            )
            manageItemViewModel.update(currentIdList!!, item).let {
                goBack()
            }
        }
    }

    private fun saveItem() {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString().toInt()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()

        val categoryEnum = Category.entries.firstOrNull { it.getName() == itemCategory }
        val unitEnum = UnitOfMeasure.entries.firstOrNull { it.getName() == itemUnit }

        if (categoryEnum == null || unitEnum == null) {
            Toast.makeText(this, "Erro: Categoria ou unidade inválida.", Toast.LENGTH_SHORT).show()
        } else {
            val item = ShoppingItem(
                id = manageItemViewModel.getNextId(currentIdList!!) + 1,
                name = itemName.first().uppercase() + itemName.substring(1),
                image = categoryEnum.getIcon(),
                quantity = itemQuantity,
                unity = unitEnum,
                category = categoryEnum
            )
            manageItemViewModel.add(item, currentIdList!!)
            goBack()
        }
    }


    private fun validateFields(): Boolean {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString()

        if (itemName.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o nome", Toast.LENGTH_SHORT).show()
            return false
        } else if (itemQuantity.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha a quantidade", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getDataIntentForEditItem() {
        var item = intent.getSerializableExtra("listData") as ShoppingItem?
        if (item != null) {
            shoppingItem = item
            binding.nameField.setText(item.name)
            binding.quantityField.setText(item.quantity.toString())
            binding.categorySpinner.setSelection(item.category.ordinal)
            binding.unitSpinner.setSelection(item.unity.ordinal)
            binding.saveButton.text = "Atualizar"
            binding.titleTextView.text = "Editar Item"
        }

        currentIdList = intent.getIntExtra("idListListOfList", -1)
        if (currentIdList == -1) {
            Toast.makeText(this, "Erro: Lista não encontrada.", Toast.LENGTH_SHORT).show()
            goBack()
        }
    }

    private fun goBack() {
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}