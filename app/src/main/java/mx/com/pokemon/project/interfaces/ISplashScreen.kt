package mx.com.pokemon.project.interfaces

import android.os.Handler
import android.os.Looper

interface ISplashScreen {

    fun initSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({

        }, 2000)
    }
}