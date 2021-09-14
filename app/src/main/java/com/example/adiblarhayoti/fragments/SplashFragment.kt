package com.example.adiblarhayoti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSplashBinding.inflate(inflater, container, false)
        (activity as MainActivity?)?.hideBottomNavBar()
        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep((2.5 * 1000).toLong())
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.homeFragment)
                } catch (e: Exception) {
                }
            }
        }
        background.start()

        return binding.root
    }
}