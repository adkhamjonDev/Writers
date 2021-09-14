package com.example.adiblarhayoti.fragments
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.databinding.FragmentSettingsBinding
import com.example.adiblarhayoti.databinding.ModuleRemoveDialogBinding

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSettingsBinding.inflate(inflater, container, false)
        (activity as MainActivity?)?.showBottomNavBar()
        binding.share.setOnClickListener {
            intentMessageTelegram("https://play.google.com/store/apps/details?id=uz.mobiler.adiblar")
        }
        binding.info.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val binding1 = ModuleRemoveDialogBinding.inflate(inflater, null, false)
            builder.setView(binding1.root)
            val alertDialog = builder.create()
            alertDialog.show()
        }
        return binding.root
    }
    fun intentMessageTelegram(msg: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        context?.startActivity(Intent.createChooser(intent, "choose one"))
    }

}