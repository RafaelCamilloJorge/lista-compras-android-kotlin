package com.example.listadecompras.feature.manage_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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

class ManageListActivity : AppCompatActivity(), ManageListContracts.View {

    private lateinit var binding: ActivityManageListBinding
    private var selectedImage: Uri? = null
    private val manageListViewModel: ManageListViewModel by viewModel()
    private var shoppingListOfList: ShoppingListOfList? = null
    private var imageName: String? = null

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

        getDataIntentForEditList()

        binding.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            startForResult.launch(intent)
        }

        binding.saveButton.setOnClickListener {
            val listName = binding.nameField.text.toString()
            if (listName.isNotEmpty()) {
                if (shoppingListOfList != null) editList(shoppingListOfList!!) else getIdToCreateNewList()
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
                selectedImage?.let { uri ->
                    Glide.with(this)
                        .load(uri)
                        .centerCrop()
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .into(binding.listImageImageView)
                }
                imageName = selectedImage.toString()
            }
        }

    private fun getIdToCreateNewList() {
        manageListViewModel.getNextId(onSuccess = {
            createNewList(it)
        }, onError = {
            showError(it)
        })
    }

    private fun createNewList(id: Int) {
        val listName = binding.nameField.text.toString()
        val newList = ShoppingListOfList(
            id = id,
            name = listName.first().uppercase() + listName.substring(1),
            image = imageName,
            shoppingList = mutableListOf()
        )
        manageListViewModel.add(newList, onSuccess = {
            goBack()
            showError("Lista criada!")
        }, onError = { showError(it) })
    }

    private fun editList(list: ShoppingListOfList) {
        val listName = binding.nameField.text.toString()

        val newList = ShoppingListOfList(
            id = list.getIdList(),
            name = listName.first().uppercase() + listName.substring(1),
            image = imageName ?: list.image,
            shoppingList = list.getItems()
        )
        manageListViewModel.update(list.getIdList(), newList, onSuccess = {
            goBack()
            showError("Lista atualizada!")
        }, onError = {
            showError(it)
        })
    }

    override fun goBack() {
        val resultIntent = Intent()
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun getDataIntentForEditList() {
        val id = intent.getIntExtra("listId", -1)
        if (id != -1) {
            manageListViewModel.getById(id, onSuccess = {
                it.let {
                    binding.nameField.setText(shoppingListOfList!!.getNameList())
                    binding.saveButton.text = "Atualizar"
                    binding.titleTextView.text = "Editar lista"
                    loadImageWithGlideIfExist()
                }
            }, onError = { showError(it) })
        }
    }

    private fun loadImageWithGlideIfExist() {
        shoppingListOfList?.image?.let { imagePath ->
            Glide.with(this)
                .load(Uri.parse(imagePath))
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(binding.listImageImageView)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
