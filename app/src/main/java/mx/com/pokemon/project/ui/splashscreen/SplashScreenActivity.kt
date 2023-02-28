package mx.com.pokemon.project.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.pokemon.project.databinding.SplashscreenActivityBinding
import mx.com.pokemon.project.interfaces.ISplashScreen

class SplashScreenActivity: AppCompatActivity(), ISplashScreen {
    private lateinit var binding: SplashscreenActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitView()
        initSplashScreen()
    }

    private fun setInitView(){
        binding = SplashscreenActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}