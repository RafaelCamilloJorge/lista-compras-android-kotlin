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

class RegisterActivity : AppCompatActivity() {
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
            if (binding.loginField.text.toString().trim()
                    .isEmpty() || binding.passwordField.text.toString().trim().isEmpty()
            ) {
                showErrorMsg("Preencha todos os campos")
                return@setOnClickListener
            } else if (binding.passwordField.text.toString() != binding.confirmPasswordField.text.toString()) {
                showErrorMsg("As senhas não coincidem")
                return@setOnClickListener
            }

            val result: OnResult<Boolean> = registerViewModel.register(
                binding.loginField.text.toString(),
                binding.passwordField.text.toString()
            )
            when (result) {
                is OnResult.Success -> createAccountMsg()
                is OnResult.Error ->
                    showErrorMsg(result.exception.messageError())

                else ->
                    showErrorMsg("Erro desconhecido ao criar conta.")

            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorMsg(errorMessage: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(errorMessage)
            .setTitle("Erro ao criar conta")
            .setNeutralButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun createAccountMsg() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("Sucesso")
            .setMessage("Conta criada com sucesso!")
            .setNeutralButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}