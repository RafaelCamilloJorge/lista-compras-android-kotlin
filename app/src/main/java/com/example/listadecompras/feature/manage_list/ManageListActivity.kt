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
import com.bumptech.glide.Glide
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityManageListBinding
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageListBinding
    private var selectedImage: Uri? = null
    private val manageListViewModel: ManageListViewModel by viewModel()
    private lateinit var list: ShoppingListOfList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val idList = intent.getIntExtra("idList", -1)

        if(idList != -1){
            binding.titleTextView.text = "Editar Lista"
            binding.saveButton.text = "Atualizar"

            list = manageListViewModel.getById(idList)
            binding.nameField.setText(list.getNameList())

           
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
                if(idList != -1) editList(list) else createNewList()
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

    private fun editList(list: ShoppingListOfList) {
        val listName = binding.nameField.text.toString()
        val newList = ShoppingListOfList(
            id = list.getIdList(),
            name = listName.first().uppercase() + listName.substring(1),
            image = selectedImage.toString(),
            shoppingList = list.getItems()
        )

        manageListViewModel.update(list.getIdList(), newList)
        goBack()
    }

    private fun goBack() {
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
