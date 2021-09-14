package com.example.adiblarhayoti.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.adiblarhayoti.R
import com.example.adiblarhayoti.adapters.RvAdapter
import com.example.adiblarhayoti.databinding.FragmentPagerBinding
import com.example.adiblarhayoti.db.AdibUrl
import com.example.adiblarhayoti.db.AppDatabase
import com.example.adiblarhayoti.models.WritersClass
import com.google.firebase.firestore.FirebaseFirestore
private const val ARG_PARAM1 = "param1"
class PagerFragment : Fragment() {
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<AdibUrl>
    lateinit var obj: AdibUrl
    private var param1: String? = null
    lateinit var binding:FragmentPagerBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var allList:ArrayList<WritersClass>
    private val TAG = "PagerFragment"
    lateinit var firebaseFirestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentPagerBinding.inflate(inflater, container, false)
        firebaseFirestore= FirebaseFirestore.getInstance()
        appDatabase = AppDatabase.getInstance(requireContext())
        allList= ArrayList()
        list= ArrayList(appDatabase.userDao().getUsers())
        firebaseFirestore.collection("adiblar").whereEqualTo("category",param1).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val writersClass=document.toObject(WritersClass::class.java)
                    allList.add(writersClass)
                }
                rvAdapter= RvAdapter(allList,object:RvAdapter.OnItemClickListener{
                    override fun onItemMusic(writersClass: WritersClass) {
                        val bundle= bundleOf("adib" to writersClass)
                        findNavController().navigate(R.id.infoFragment,bundle)
                    }
                    override fun onItemSaved(imageView: ImageView, imageView1: ImageView,
                                             writersClass: WritersClass,cardView: CardView) {
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
                    override fun onItemUnSaved(imageView: ImageView, imageView1: ImageView,
                                               writersClass: WritersClass,cardView: CardView) {
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
                })
                binding.recView.adapter=rvAdapter
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}