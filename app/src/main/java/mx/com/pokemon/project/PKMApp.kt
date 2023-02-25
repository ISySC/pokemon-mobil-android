package mx.com.pokemon.project

import android.app.Application
import android.content.Context

class PKMApp : Application() {
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = this
        DInjection.set(context)
    }
}