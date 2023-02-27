package mx.com.pokemon.project.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.pokemon.project.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitView()
    }

    private fun setInitView() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}