package com.example.listadecompras.feature.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityMainBinding
import com.example.listadecompras.feature.register.RegisterActivity
import com.example.listadecompras.feature.shopping_items.ShoppingItemActivity
import com.example.listadecompras.feature.shopping_lists.ShoppingListActivity
import com.example.listadecompras.repositories.LoginRepository
import com.example.listadecompras.repositories.interfaces.ILoginRepository


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val _repository = LoginRepository()
    private val loginViewModel = LoginViewModel(_repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.loginButton.setOnClickListener {
            val username = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            if (username.isBlank() || password.isBlank()) {
                showErrorMsg("É necessário preencher o usuário e a senha.")
            } else {
                val result = loginViewModel.login(username, password)

                when (result) {
                    is OnResult.Success -> {
                        val intent = Intent(this, ShoppingListActivity::class.java)
                        startActivity(intent)
                    }
                    is OnResult.Error -> {
                        showErrorMsg("Credenciais inválidas.")
                    }
                    else -> {
                        showErrorMsg("Erro ao tentar logar.")
                    }
                }
            }
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMsg(errorMessage: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(errorMessage)
            .setTitle("Erro ao logar")
            .setNeutralButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
