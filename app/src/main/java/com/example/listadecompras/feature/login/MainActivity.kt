package com.example.listadecompras.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityMainBinding
import com.example.listadecompras.feature.register.RegisterActivity
import com.example.listadecompras.feature.shopping_lists.ShoppingListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LoginContracts.View {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModel()

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
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            if (email.isBlank() || password.isBlank()) {
                showError("É necessário preencher o usuário e a senha.")
                return@setOnClickListener
            } else if (!isValidEmail(email)) {
                showError("Email inválido.")
                return@setOnClickListener
            } else {
                login(email, password)
            }
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun login(email: String, password: String) {
        loginViewModel.login(email, password,
            onSuccess = { data ->
                navigateToListView()
                binding.emailField.text.clear()
                binding.passwordField.text.clear()
            },
            onError = { error ->
                showError(error)
            })
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToListView() {
        val intent = Intent(this, ShoppingListActivity::class.java)
        startActivity(intent)
    }
}
