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
            if (saveItem(idList)) {
                val resultIntent = Intent()
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

    }

    fun saveItem(idList: Int): Boolean {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString().toInt()
        val itemCategory = binding.categorySpinner.selectedItem.toString()
        val itemUnit = binding.unitSpinner.selectedItem.toString()

        if (!validateFields()) {
            return false
        }
        val categoryEnum = Category.values().firstOrNull { it.getName() == itemCategory }
        val unitEnum = UnitOfMeasure.values().firstOrNull { it.getName() == itemUnit }

        if (categoryEnum == null || unitEnum == null) {
            Toast.makeText(this, "Erro: Categoria ou unidade inválida.", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        val item = ShoppingItem(
            id = manageItemViewModel.getNextId(idList) + 1,
            name = itemName.first().uppercase() + itemName.substring(1),
            image = categoryEnum.getIcon(),
            quantity = itemQuantity,
            unity = unitEnum,
            category = categoryEnum
        )
        manageItemViewModel.add(item, idList)
        return true
    }


    fun validateFields(): Boolean {
        val itemName = binding.nameField.text.toString()
        val itemQuantity = binding.quantityField.text.toString()

        if(itemName.isEmpty()){
            Toast.makeText(this, "Por favor, preencha o nome", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(itemQuantity.isEmpty()){
            Toast.makeText(this, "Por favor, preencha a quantidade", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

}