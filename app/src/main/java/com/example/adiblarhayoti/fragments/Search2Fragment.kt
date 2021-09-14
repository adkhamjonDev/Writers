package com.example.adiblarhayoti.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.RvAdapter1
import com.example.adiblarhayoti.databinding.FragmentSearch2Binding
import com.example.adiblarhayoti.db.AdibUrl
import com.example.adiblarhayoti.db.AppDatabase

class Search2Fragment : Fragment() {
    lateinit var binding: FragmentSearch2Binding
    lateinit var appDatabase: AppDatabase
    lateinit var obj:AdibUrl
    lateinit var list: ArrayList<AdibUrl>
    lateinit var rvAdapter: RvAdapter1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentSearch2Binding.inflate(inflater, container, false)
        appDatabase= AppDatabase.getInstance(requireContext())
        list=ArrayList(appDatabase.userDao().getUsers())

        rvAdapter= RvAdapter1(list,object: RvAdapter1.OnItemClickListener{
            override fun onItemMusic(writersClass: AdibUrl) {
                val bundle= bundleOf("save" to writersClass)
                findNavController().navigate(R.id.infoFragment2,bundle)
            }
            override fun onItemSaved(imageView: ImageView, imageView1: ImageView,
                                     writersClass: AdibUrl
            ) {
                imageView.visibility=View.GONE
                imageView1.visibility=View.VISIBLE
                for (i in 0 until list.size){
                    if(list[i].imgUrl==writersClass.imgUrl){
                        obj=list[i]
                        break
                    }
                }
                appDatabase.userDao().deleteUser(obj)
            }
            override fun onItemUnSaved(imageView: ImageView, imageView1: ImageView,
                                       writersClass: AdibUrl
            ) {
                imageView.visibility=View.GONE
                imageView1.visibility=View.VISIBLE
            }
        })
        binding.recView.adapter=rvAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                rvAdapter.filter.filter(newText)
                return false
            }
        })
        return binding.root
    }
}