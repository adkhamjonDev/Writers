package com.example.adiblarhayoti.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblarhayoti.databinding.CustomItem1Binding
import com.example.adiblarhayoti.db.AdibUrl
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList
class RvAdapter1(var list: ArrayList<AdibUrl>, var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<RvAdapter1.MyViewHolder>(),Filterable {
    val list1=ArrayList<AdibUrl>(list)
    inner class MyViewHolder(var customItem1Binding: CustomItem1Binding): RecyclerView.ViewHolder(
        customItem1Binding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomItem1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj=list[position]
        Picasso.get().load(obj.imgUrl).into(holder.customItem1Binding.image)
        val name=obj.fullName
        var pos=0
        for (i in name!!.indices) {
            if (name[i]==' ') {
                pos=i
                break
            }
        }
        val n=name.substring(0,pos)
        val s=name.substring(pos+1)
        holder.customItem1Binding.name.text="${n}\n${s}"
        holder.customItem1Binding.date.text="(${obj.dateOfBirth})"
        holder.customItem1Binding.card.setOnClickListener {
            onItemClickListener.onItemMusic(obj)
        }
        holder.customItem1Binding.save.setOnClickListener {
            onItemClickListener.onItemSaved(holder.customItem1Binding.save,holder.customItem1Binding.unsave,
            obj)
        }
        holder.customItem1Binding.unsave.setOnClickListener {
            onItemClickListener.onItemUnSaved(holder.customItem1Binding.unsave,holder.customItem1Binding.save,obj
            )
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemMusic(writersClass: AdibUrl)
        fun onItemSaved(imageView: ImageView,imageView1: ImageView,writersClass: AdibUrl)
        fun onItemUnSaved(imageView: ImageView,imageView1: ImageView,writersClass: AdibUrl)
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }
    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val newList=ArrayList<AdibUrl>()
            if(constraint==null|| constraint.isEmpty()){
                newList.addAll(list1)
            }else{
                val filterPattern= constraint.toString().lowercase(Locale.getDefault()).trim()
                for (i in 0 until list1.size){
                    if(list1[i].fullName?.lowercase(Locale.getDefault())!!.contains(filterPattern)){
                        newList.add(list1[i])
                    }
                }
            }
            val results=FilterResults()
            results.values=newList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            list.clear()
            list.addAll(results.values as ArrayList<AdibUrl>)
            notifyDataSetChanged()
        }
    }
}