package com.hung.wallup.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.hung.wallup.databinding.ItemCategoriesAdapterBinding
import com.hung.wallup.domain.model.Category


class CatagoryListAdapter :
    RecyclerView.Adapter<CatagoryListAdapter.CategoryViewHolder>() {
    private var listCategory= mutableListOf<Category>()
    private var listener :((Category)->Unit)?=null

    fun setContentList(list: MutableList<Category>) {
        this.listCategory = list
        notifyItemRangeChanged(0, list.size)
    }

    fun itemClickListener(l:(Category)->Unit){
        listener= l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoriesAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item: Category = listCategory[position]
        holder.viewHolder.ivCategory.setBackgroundResource(item.imageRes)
        holder.viewHolder.tvTitle.text = item.title
        holder.viewHolder.view.setOnClickListener {
            listener?.let {
                it(this.listCategory[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    class CategoryViewHolder(val viewHolder: ItemCategoriesAdapterBinding) :
        RecyclerView.ViewHolder(viewHolder.root)
}