package com.james.challenge_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.activity.viewModels
import com.james.challenge_4.databinding.ActivityRegisterBinding
import com.james.challenge_4.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel : SplashViewModel by viewModels()
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel.isloading.observe(this){
            if(it){
                binding.pb.visibility = View.VISIBLE
            } else{
                binding.pb.visibility = View.VISIBLE
            }
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        viewModel.checklogin()

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.status.observe(this@SplashActivity){
                 if (it) {
                    // The content is ready. Start drawing.
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                 } else {
                    // The content isn't ready. Suspend.
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }, 1000)
    }
}