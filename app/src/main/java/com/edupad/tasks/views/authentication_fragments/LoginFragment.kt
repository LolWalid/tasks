package com.edupad.tasks.views.authentication_fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.edupad.tasks.R
import com.edupad.tasks.models.UserLogin
import com.edupad.tasks.services.Api
import com.edupad.tasks.views.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

private val coroutineScope = MainScope()

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.submit_button.setOnClickListener { submitForm() }
        return view
    }

    private fun submitForm() {
        val form = UserLogin(email_edit_text.text.toString(), password_edit_text.text.toString())

        coroutineScope.launch {
            val response = Api.INSTANCE.userService.login(form)
            if (response.isSuccessful) {
                val token = response.body()?.token
                token?.let {
                    val sharedPref = context?.getSharedPreferences("tasks_preferences", Context.MODE_PRIVATE)?.edit()
                    sharedPref?.putString("JWT_TOKEN", it)
                    sharedPref?.apply()
                    Log.e("GlobalScope", token)
                    openMainActivity()
                }
            }
        }
    }

    private fun openMainActivity() {
        val intent = Intent(activity!!, MainActivity::class.java)
        startActivity(intent)
    }
}
