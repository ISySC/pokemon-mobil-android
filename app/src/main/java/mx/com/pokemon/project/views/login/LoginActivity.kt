package mx.com.pokemon.project.views.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.pokemon.project.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitView()
    }

    private fun setInitView() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}