package com.dream.uieffectdemo.imedittext

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dream.uieffectdemo.databinding.ItemContactViewBinding

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/7
 */
class ContactSelectAdapter: RecyclerView.Adapter<ContactSelectAdapter.MyViewHolder>(){


    private val mDataList = mutableListOf<ContactSelectBean>()
    var onItemCheckListener: ((selectData: ContactSelectBean,position: Int,isChecked: Boolean)->Unit)? = null

    init {
        for (i in 1..10){
            mDataList.add(ContactSelectBean("erdai$i","12345$i"))
        }
    }

    class MyViewHolder(val binding: ItemContactViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: ContactSelectBean){
            binding.info = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemContactViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mDataList[position])
        holder.binding.cb.setOnCheckedChangeListener { _, b ->
            onItemCheckListener?.invoke(mDataList[position],position,b)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }
}