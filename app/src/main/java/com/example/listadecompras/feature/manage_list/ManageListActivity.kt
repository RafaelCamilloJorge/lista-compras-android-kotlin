package com.example.listadecompras.feature.manage_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityManageListBinding
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageListBinding
    private var selectedImage: Uri? = null
    private val manageListViewModel: ManageListViewModel by viewModel()
    private var shoppingListOfList: ShoppingListOfList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataIntentForEditList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startForResult.launch(intent)
        }

        binding.saveButton.setOnClickListener {
            val listName = binding.nameField.text.toString()
            if (listName.isNotEmpty()) {
                if (shoppingListOfList != null) editList() else createNewList()
            } else {
                Toast.makeText(this, "Por favor, preencha o nome", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                selectedImage = data?.data
                binding.listImageImageView.setImageURI(selectedImage)
            }
        }

    private fun createNewList() {
        val listName = binding.nameField.text.toString()

        val newList = ShoppingListOfList(
            id = manageListViewModel.getNextId(),
            name = listName.first().uppercase() + listName.substring(1),
            image = selectedImage.toString(),
            shoppingList = mutableListOf()
        )

        manageListViewModel.add(newList)
        goBack()
    }

    private fun editList() {
        val listName = binding.nameField.text.toString()
        val newList = ShoppingListOfList(
            id = shoppingListOfList!!.getIdList(),
            name = listName.first().uppercase() + listName.substring(1),
            image = selectedImage.toString(),
            shoppingList = shoppingListOfList!!.getItems()
        )

        manageListViewModel.update(shoppingListOfList!!.getIdList(), newList)
        goBack()
    }

    private fun goBack() {
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun getDataIntentForEditList() {
        val list = intent.getSerializableExtra("listData") as ShoppingListOfList?
        if (list != null) {
            shoppingListOfList = list
            binding.nameField.setText(list.getNameList())
            binding.listImageImageView.setImageURI(list.getImageList())
            binding.saveButton.text = "Atualizar"
            binding.titleTextView.text = "Editar lista"
        }
    }
}
