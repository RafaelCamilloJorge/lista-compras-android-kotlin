package com.example.listadecompras.feature.register

import OnResult
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityMainBinding
import com.example.listadecompras.databinding.ActivityRegisterBinding
import com.example.listadecompras.feature.login.LoginViewModel
import com.example.listadecompras.feature.login.MainActivity
import com.example.listadecompras.repositories.LoginRepository
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

import android.util.Patterns
import android.widget.Toast
import com.example.listadecompras.commons.validates.LoginValidate

class RegisterActivity : AppCompatActivity(), RegisterContracts.View {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.registerButton.setOnClickListener {
            val statusValidate = LoginValidate().validateCreateAccount(
                binding.userNameField.text.toString(),
                binding.emailField.text.toString(),
                binding.passwordField.text.toString(),
                binding.confirmPasswordField.text.toString(),
            )

            if (statusValidate != null) {
                showError(statusValidate)
            } else {
                registerViewModel.register(
                    binding.emailField.text.toString(),
                    binding.userNameField.text.toString(),
                    binding.passwordField.text.toString(),
                    onSuccess = {
                        showError("Conta criada com sucesso!")
                        goBackToLoginActivity()
                    },
                    onError = {
                        showError(it)
                    })
            }
        }

        binding.backButton.setOnClickListener {
            goBackToLoginActivity()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goBackToLoginActivity() {
        finish()
    }
}
