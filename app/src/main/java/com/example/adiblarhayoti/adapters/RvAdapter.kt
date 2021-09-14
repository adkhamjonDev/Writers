package com.example.adiblarhayoti.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.adiblarhayoti.databinding.CustomItemBinding
import com.example.adiblarhayoti.models.WritersClass
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter( var list: ArrayList<WritersClass>, var onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<RvAdapter.MyViewHolder>(),Filterable {
    val list1=ArrayList<WritersClass>(list)
    inner class MyViewHolder(var customItemBinding: CustomItemBinding): RecyclerView.ViewHolder(
        customItemBinding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj=list[position]
        Picasso.get().load(obj.imgUrl).into(holder.customItemBinding.image)
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
        holder.customItemBinding.name.text="${n}\n${s}"
        holder.customItemBinding.date.text="(${obj.dateOfBirth})"
        holder.customItemBinding.card.setOnClickListener {
            onItemClickListener.onItemMusic(obj)
        }
        holder.customItemBinding.save.setOnClickListener {
            onItemClickListener.onItemSaved(holder.customItemBinding.save,holder.customItemBinding.unsave,
            obj,holder.customItemBinding.cardsavec)
        }
        holder.customItemBinding.unsave.setOnClickListener {
            onItemClickListener.onItemUnSaved(holder.customItemBinding.unsave,holder.customItemBinding.save,obj
            ,holder.customItemBinding.cardsavec)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener {
        fun onItemMusic(writersClass: WritersClass)
        fun onItemSaved(imageView: ImageView,imageView1: ImageView,writersClass: WritersClass,cardView: CardView)
        fun onItemUnSaved(imageView: ImageView,imageView1: ImageView,writersClass: WritersClass,cardView: CardView)
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }
    private val exampleFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val newList=ArrayList<WritersClass>()
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
            list.addAll(results.values as ArrayList<WritersClass>)
            notifyDataSetChanged()
        }
    }
}