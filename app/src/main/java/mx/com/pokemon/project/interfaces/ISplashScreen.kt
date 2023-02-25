package mx.com.pokemon.project.interfaces

import android.os.Handler
import android.os.Looper
import mx.com.pokemon.project.DInjection
import mx.com.pokemon.project.utils.Utils.Companion.showActivity
import mx.com.pokemon.project.views.login.LoginActivity

interface ISplashScreen {

    fun initSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            showActivity(DInjection.context().applicationContext, LoginActivity::class.java)
        }, 2000)
    }
}