package com.example.adiblarhayoti.fragments

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.databinding.FragmentInfo2Binding
import com.example.adiblarhayoti.databinding.FragmentInfoBinding
import com.example.adiblarhayoti.db.AdibUrl
import com.example.adiblarhayoti.models.WritersClass
import com.squareup.picasso.Picasso

class InfoFragment2 : Fragment() {
    lateinit var binding: FragmentInfo2Binding
    lateinit var obj: AdibUrl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentInfo2Binding.inflate(inflater, container, false)
        (activity as MainActivity?)?.hideBottomNavBar()
        if(arguments!=null){
            obj=arguments?.get("save")as AdibUrl
        }
        Picasso.get().load(obj.imgUrl).into(binding.image)
        binding.txt.text=obj.description
        binding.date.text=obj.dateOfBirth
        binding.fullname.text=obj.fullName
        binding.dash.title=obj.fullName
        binding.button.setOnClickListener(View.OnClickListener {
            val criteria: String = binding.edit.text.toString()
            var fullText: String = binding.txt.text.toString()
            if (fullText.contains(criteria)) {
                val indexOfCriteria = fullText.indexOf(criteria)
                val lineNumber: Int = binding.txt.layout.getLineForOffset(indexOfCriteria)
                val highlighted = "<font color='red'>$criteria</font>"
                fullText = fullText.replace(criteria, highlighted)
                binding.txt.text = Html.fromHtml(fullText)
                binding.scrollView.scrollTo(0, binding.txt.layout.getLineTop(lineNumber))
            }
        })
        binding.edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isEmpty()) {
                    var fullText: String = binding.txt.text.toString()
                    fullText = fullText.replace("<font color='red'>", "")
                    fullText = fullText.replace("</font>", "")
                    binding.txt.text = fullText
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        return  binding.root
    }

}