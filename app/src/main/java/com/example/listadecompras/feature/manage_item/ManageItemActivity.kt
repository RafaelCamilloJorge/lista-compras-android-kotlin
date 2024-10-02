package com.example.listadecompras.feature.manage_item

import com.example.listadecompras.utils.Category
import ShoppingItem
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.commons.validates.ItemsValidate
import com.example.listadecompras.databinding.ActivityManageItemBinding
import com.example.listadecompras.utils.UnitOfMeasure
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageItemActivity : AppCompatActivity(), ManageItemContracts.View {

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

        val idList = intent.getIntExtra("idList", -1)
        val idItem = intent.getIntExtra("idItem", -1)

        if (idItem != -1) {
            binding.titleTextView.text = "Editar item"
            binding.saveButton.text = "Atualizar"

            manageItemViewModel.getItemById(idList, idItem, onSuccess = {
                binding.nameField.setText(it.name)
                binding.quantityField.setText(it.quantity.toString())
                binding.categorySpinner.setSelection(Category.values().indexOf(it.category))
                binding.unitSpinner.setSelection(it.unity.ordinal)
            }, onError = {
                showError(it)
            })
        }

        binding.saveButton.setOnClickListener {
            if (idItem != -1) {
                validateToUpdateItem(idList, idItem)
            } else {
                validateToAddItem(idList)
            }
        }
    }

    private fun validateToAddItem(idList: Int) {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()

        val categoryEnum = Category.entries.firstOrNull { it.getName() == itemCategory }
        val unitEnum = UnitOfMeasure.entries.firstOrNull { it.getName() == itemUnit }

        val error: String? =
            ItemsValidate().validateFieldsItem(
                itemName,
                itemQuantity,
                categoryEnum,
                unitEnum
            )

        if (error != null) {
            showError(error)
        } else {
            manageItemViewModel.getNextId(idList, onSuccess = {
                val item = ShoppingItem(
                    id = it,
                    name = itemName.first().uppercase() + itemName.substring(1),
                    image = categoryEnum!!.getIcon(),
                    quantity = itemQuantity.toInt(),
                    unity = unitEnum!!,
                    category = categoryEnum
                )
                create(item, idList)
            }, onError = {
                showError(it)
            })
        }
    }

    private fun create(item: ShoppingItem, idList: Int) {
        manageItemViewModel.add(item, idList, onSuccess = {
            goBack()
            showError("Item adicionado!")
        }, onError = {
            showError(it)
        })
    }

    private fun validateToUpdateItem(idList: Int, idItem: Int) {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString().toInt()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()

        val categoryEnum = Category.entries.firstOrNull { it.getName() == itemCategory }
        val unitEnum = UnitOfMeasure.entries.firstOrNull { it.getName() == itemUnit }

        val error: String? =
            ItemsValidate().validateFieldsItem(
                itemName,
                itemQuantity.toString(),
                categoryEnum,
                unitEnum
            )

        if (error != null) {
            showError(error)
            return
        } else {
            val updateItem = ShoppingItem(
                id = idItem,
                name = itemName.first().uppercase() + itemName.substring(1),
                image = categoryEnum!!.getIcon(),
                quantity = itemQuantity,
                unity = unitEnum!!,
                category = categoryEnum
            )
            validateToUpdateItem(updateItem, idList)
        }
    }

    private fun validateToUpdateItem(newItem: ShoppingItem, idList: Int) {
        manageItemViewModel.update(idList, newItem.id, newItem, onSuccess = {
            goBack()
        }, onError = {
            showError(it)
        })
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goBack() {
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }

}
