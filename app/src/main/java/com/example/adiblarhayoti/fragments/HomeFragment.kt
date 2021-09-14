package com.example.adiblarhayoti.fragments
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.MyPagerAdapter
import com.example.adiblarhayoti.databinding.CustomItemBinding
import com.example.adiblarhayoti.databinding.CustomTabBinding
import com.example.adiblarhayoti.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
class HomeFragment : Fragment() {
    lateinit var bind:CustomTabBinding
    lateinit var binding: FragmentHomeBinding
    lateinit var categoryList:ArrayList<String>
    lateinit var myPagerAdapter: MyPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        (activity as MainActivity?)?.showBottomNavBar()
        categoryList=ArrayList<String>()
        categoryList.add("Mumtoz adabiyoti")
        categoryList.add("O’zbek adabiyoti")
        categoryList.add("Jahon adabiyoti")
        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
        myPagerAdapter= MyPagerAdapter(categoryList,requireContext(), childFragmentManager)
        binding.viewPager.adapter=myPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setTabs()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabView = tab.customView
                val card=tabView!!.findViewById<CardView>(R.id.card)
                val tittle=tabView.findViewById<TextView>(R.id.tittle)
                card.setCardBackgroundColor(Color.parseColor("#00B238"))
                tittle.setTextColor(Color.WHITE)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabView = tab.customView
                val card=tabView!!.findViewById<CardView>(R.id.card)
                val tittle=tabView.findViewById<TextView>(R.id.tittle)
                card.setCardBackgroundColor(Color.WHITE)
                tittle.setTextColor(Color.parseColor("#303236"))
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return binding.root
    }
    private fun setTabs() {
        val count: Int = binding.tabLayout.tabCount
        for (i in 0 until count) {
            bind = CustomTabBinding.inflate(layoutInflater, null, false)
            bind.tittle.text = categoryList[i]
            if (i == 0) {
                bind.card.setCardBackgroundColor(Color.parseColor("#00B238"))
                bind.tittle.setTextColor(Color.WHITE)
            } else {
                bind.card.setCardBackgroundColor(Color.WHITE)
                bind.tittle.setTextColor(Color.parseColor("#303236"))
            }
            binding.tabLayout.getTabAt(i)?.customView = bind.root
        }
    }
}