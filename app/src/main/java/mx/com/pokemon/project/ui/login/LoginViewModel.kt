package mx.com.pokemon.project.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import mx.com.pokemon.project.utils.SingleLiveEvent
import mx.com.pokemon.project.utils.Utils

class LoginViewModel : ViewModel() {

    private var _isValidPass = SingleLiveEvent<Boolean>()
    val isValidPass: LiveData<Boolean> = _isValidPass

    private var _isValidUser = SingleLiveEvent<Boolean>()
    val isValidUser: LiveData<Boolean> = _isValidUser

    private var _isEnableLoginButton = MutableLiveData(false)
    val isEnableLoginButton: LiveData<Boolean> = _isEnableLoginButton

    fun loginUserNameDataChanged(username: String) {
        _isValidUser.value = Utils.isValidMail(username)
        isEnableLogin()
    }

    fun loginPasswordDataChanged(password: String) {
        _isValidPass.value = Utils.isValidPassword(password)
        isEnableLogin()
    }

    private fun isEnableLogin(){
        _isEnableLoginButton.value = _isValidPass.value == true && _isValidUser.value == true

    }
}