package com.james.challenge_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.james.challenge_4.databinding.ActivityLoginBinding
import com.james.challenge_4.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (binding.tieEmail.text.isNullOrEmpty()) {
                binding.tieEmail.error = "email tidak boleh kosong"
            } else if (binding.tiePassword.text.isNullOrEmpty()) {
                binding.tiePassword.error = "password tidak boleh kosong"
            } else {
                binding.tieEmail.error = null
                binding.tiePassword.error = null
                viewModel.login(
                    email = binding.tieEmail.text.toString(),
                    password = binding.tiePassword.text.toString(),
                )
            }
        }

        binding.buttonMove.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.success.observe(this) { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else{
                Toast.makeText(this, "Login Gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}