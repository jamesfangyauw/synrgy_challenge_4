package com.james.challenge_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.james.challenge_4.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.buttonRegister.setOnClickListener {
            var email = binding.tieEmail.text.toString()
            var pass = binding.tiePassword.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() ) {
                viewModel.register(email, pass)

            } else {
                binding.tilEmail.error = "email dan password harap diisi dengan benar"
            }

        }

        viewModel.isSuccess.observe(this){
            if(it) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }

    }
}