package mx.com.pokemon.project.utils

import android.content.Context
import android.content.Intent
import android.util.Patterns
import java.util.regex.Pattern

class Utils {
    companion object {

        fun showActivity(context: Context, target: Class<*>) {
            context.startActivity(
                Intent(
                    context,
                    target
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }

        fun esCorreoValido(correo: String): Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        }

        fun isEmptyValue(vararg values: String): Boolean {
            for (value in values) {
                if (value.isEmpty()) {
                    return true
                } else continue
            }
            return false
        }
    }
}