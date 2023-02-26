package mx.com.pokemon.project.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText

import mx.com.pokemon.project.R
import mx.com.pokemon.project.databinding.LoginActivityBinding
import mx.com.pokemon.project.utils.Utils.Companion.afterTextChanged

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: LoginActivityBinding

    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var login: AppCompatButton
    private lateinit var loading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitView()
        setInitObservers()
        setInitWatchers()
    }

    private fun setInitView() {
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = binding.edUsuario
        password = binding.edContrasena
        login = binding.btnInicioSesion
        loading = binding.pbLoading
    }

    private fun setInitObservers(){
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.isValidUser.observe(this@LoginActivity, Observer {
            binding.inputUsuario.error = if (it != false)
                null else getString(R.string.str_error_correo)
        })

        loginViewModel.isValidPass.observe(this@LoginActivity, Observer {
            binding.inputContrasena.error = if (it != false)
                null else getString(R.string.str_error_password)
        })

        loginViewModel.isEnableLoginButton.observe(this@LoginActivity, Observer {
            binding.btnInicioSesion.isEnabled = it

            if (binding.btnInicioSesion.isEnabled)
                binding.btnInicioSesion.backgroundTintList =
                    resources.getColorStateList(R.color.primary_color)
            else
                binding.btnInicioSesion.backgroundTintList =
                    resources.getColorStateList(R.color.gray)
        })
    }

    private fun setInitWatchers(){
        username.afterTextChanged {
            loginViewModel.loginUserNameDataChanged(
                username.text.toString()
            )
        }

        password.afterTextChanged {
            loginViewModel.loginPasswordDataChanged(
                password.text.toString()
            )
        }

        login.setOnClickListener {

        }
    }
}
