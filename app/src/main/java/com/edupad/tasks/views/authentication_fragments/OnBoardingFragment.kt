package com.edupad.tasks.views.authentication_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.edupad.tasks.R
import kotlinx.android.synthetic.main.fragment_on_boarding.view.*

class OnBoardingFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)
        view.login_button.setOnClickListener {
            view.findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

        view.signup_button.setOnClickListener {
            view.findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
        }

        return view
    }
}
