package com.james.challenge4.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.james.challenge4.presentation.dialog.PopDialog
import com.james.challenge4.R
import com.james.challenge4.databinding.ActivityRegisterBinding
import com.james.challenge4.presentation.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val pop = PopDialog()

        binding.buttonRegister.setOnClickListener {
            var email = binding.tieEmail.text.toString()
            var pass = binding.tiePassword.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() ) {
                pop.showDialog(this,
                    positive = {_,_ ->
                        viewModel.register(email,pass)
                        Toast.makeText(this, "registrasi berhasil", Toast.LENGTH_SHORT).show()
                    }, negative = {_,_->

                    },
                    title = getString(R.string.sure_delete),
                    subTitle = getString(R.string.sure_delete_sub)
                )

            } else {
                binding.tilEmail.error = "email dan password harap diisi dengan benar"
            }

        }

        viewModel.isSuccess.observe(this){
            if(it) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }

    }
}