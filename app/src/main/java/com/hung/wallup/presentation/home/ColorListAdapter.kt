package com.hung.wallup.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.hung.wallup.databinding.ItemColorToneAdapterBinding
import com.hung.wallup.domain.model.Category
import com.hung.wallup.domain.model.ColorItem


class ColorListAdapter :
    RecyclerView.Adapter<ColorListAdapter.ColorViewHolder>() {
    private var listColor = mutableListOf<ColorItem>()
    private var listener :((ColorItem)->Unit)?=null

    fun setContentList(list: MutableList<ColorItem>) {
        this.listColor = list
        notifyItemRangeChanged(0, list.size)
    }

    fun itemClickListener(l:(ColorItem)->Unit){
        listener= l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding =
            ItemColorToneAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val colorItem: ColorItem = listColor[position]
        val hexColor = android.graphics.Color.parseColor(colorItem.hexCode)
        holder.viewHolder.ivColor.setBackgroundColor(hexColor)
        holder.viewHolder.view.setOnClickListener {
            listener?.let {
                it(this.listColor[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listColor.size
    }

    class ColorViewHolder(val viewHolder: ItemColorToneAdapterBinding) :
        RecyclerView.ViewHolder(viewHolder.root)
}