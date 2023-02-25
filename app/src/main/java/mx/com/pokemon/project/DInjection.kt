package mx.com.pokemon.project

import android.annotation.SuppressLint
import android.content.Context


@SuppressLint("StaticFieldLeak")
object DInjection {
    private lateinit var context: Context

    fun set(context: Context) {
        this.context = context
    }

    fun context() : Context {
        return  this.context
    }
}