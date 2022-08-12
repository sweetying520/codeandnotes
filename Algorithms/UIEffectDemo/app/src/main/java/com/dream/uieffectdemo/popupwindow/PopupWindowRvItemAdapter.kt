package com.dream.uieffectdemo.popupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dream.uieffectdemo.R

/**
 * function: PopupWindowRvItemAdapter
 *
 * @author zy
 * @since 2022/7/4
 */
class PopupWindowRvItemAdapter(context: Context): RecyclerView.Adapter<PopupWindowRvItemAdapter.MyViewHolder>() {

    var mDataList: MutableList<String>? = null
    var layoutInflater: LayoutInflater? = null
    var onRvItemLongClickListener: ((view: View,str: String) -> Unit)? = null

    init {
        mDataList = mutableListOf()
        layoutInflater = LayoutInflater.from(context)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(mDataList: MutableList<String>?){
        this.mDataList = mDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myViewHolder = MyViewHolder(layoutInflater?.inflate(R.layout.item_popupwindow_rv_view, parent, false)!!)
        Log.d("erdai", "onCreateViewHolder: ${myViewHolder.hashCode()}")
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("erdai", "onBindViewHolder: $position ${holder.hashCode()}")
        val str = mDataList?.get(position)
        if(str != null){
            holder.tvNickName?.text = "erdai666"
            holder.tvMsg?.text = str

            holder.tvMsg?.setOnLongClickListener {
                onRvItemLongClickListener?.invoke(it,str)
                return@setOnLongClickListener true
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataList?.size?:0
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var tvNickName: TextView? = null
        var tvMsg: TextView? = null


        init {
            tvNickName = itemView.findViewById(R.id.tv_nick_name)
            tvMsg = itemView.findViewById(R.id.tv_msg)
        }
    }
}