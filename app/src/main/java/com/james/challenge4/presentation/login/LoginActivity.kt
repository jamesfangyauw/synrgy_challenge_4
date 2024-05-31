package com.james.challenge4.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.james.challenge4.presentation.MainActivity
import com.james.challenge4.presentation.register.RegisterActivity
import com.james.challenge4.databinding.ActivityLoginBinding
import com.james.challenge4.presentation.home.HomeFragment
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
                val email = binding.tieEmail.text.toString()
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(HomeFragment.EXTRA_DATA ,email )
                startActivity(intent)
                finish()
            } else{
                Toast.makeText(this, "Akun tidak ditemukan!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}