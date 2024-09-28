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
import com.example.listadecompras.feature.shopping_lists.ShoppingListViewModel
import com.example.listadecompras.presentation.ShoppingListOfList
import org.koin.androidx.viewmodel.ext.android.viewModel

class ManageListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageListBinding
    private var selectedImage: Uri? = null
    private val manageListViewModel: ManageListViewModel by viewModel()

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

        binding.fab.setOnClickListener {
            selectImageFromGallery()
        }

        binding.saveButton.setOnClickListener {
            val listName = binding.nameField.text.toString()

            if (listName.isNotEmpty()) {
                val newList = ShoppingListOfList(
                    id = manageListViewModel.getNextId(),
                    name = listName,
                    image = selectedImage.toString(),
                    shoppingList = mutableListOf()
                )

                val resultIntent = Intent()
                manageListViewModel.add(newList)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha o nome", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startForResult.launch(intent)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                selectedImage = data?.data
                binding.listImageImageView.setImageURI(selectedImage)
            }
        }
}
