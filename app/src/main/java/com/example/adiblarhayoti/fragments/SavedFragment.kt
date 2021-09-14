package com.example.adiblarhayoti.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.MainActivity
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.RvAdapter
import com.example.adiblarhayoti.adapters.RvAdapter1
import com.example.adiblarhayoti.databinding.FragmentSavedBinding
import com.example.adiblarhayoti.db.AdibUrl
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.WritersClass
class SavedFragment : Fragment() {
    lateinit var binding:FragmentSavedBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<AdibUrl>
    lateinit var obj: AdibUrl
    lateinit var rvAdapter: RvAdapter1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSavedBinding.inflate(inflater, container, false)
        (activity as MainActivity?)?.showBottomNavBar()
        appDatabase = AppDatabase.getInstance(requireContext())
        list= ArrayList(appDatabase.userDao().getUsers())
        binding.searchBtn.setOnClickListener {
            findNavController().navigate(R.id.search2Fragment)
        }
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
                val fullName=writersClass.fullName
                val date=writersClass.dateOfBirth
                val image=writersClass.imgUrl
                val description=writersClass.description
                val category=writersClass.category
                appDatabase.userDao().addUser(AdibUrl(fullName = fullName,
                    dateOfBirth = date,
                    category = category,
                    imgUrl = image
                    ,description = description,
                    false))

            }
        })
        binding.recView.adapter=rvAdapter
        return binding.root
    }
}