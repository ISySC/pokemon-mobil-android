package mx.com.pokemon.project.utils

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import mx.com.pokemon.project.DInjection
import mx.com.pokemon.project.database.PokemonInstanceDB

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

        fun isValidMail(username: String): Boolean {
            if (!isEmptyValue(username))
                return Patterns.EMAIL_ADDRESS.matcher(username).matches()

            return false
        }

        fun isValidPassword(password: String): Boolean {
            if (!isEmptyValue(password))
                return password.matches("^[$?¿¡!\\/[a-zA-Z0-9]\\s]*$".toRegex())

            return false
        }

        fun isEmptyValue(vararg values: String): Boolean {
            for (value in values) {
                if (value.isEmpty()) {
                    return true
                } else continue
            }
            return false
        }

        fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
            this.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    afterTextChanged.invoke(editable.toString())
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        }

        //funcion conectar base de datos
        fun dbConnect(): PokemonInstanceDB {
            return PokemonInstanceDB.getInstance(DInjection.context())
        }
    }
}