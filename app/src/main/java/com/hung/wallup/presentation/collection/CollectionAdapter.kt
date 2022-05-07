package com.hung.wallup.presentation.collection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hung.wallup.R
import com.hung.wallup.databinding.ItemCollectionPhotoAdapterBinding
import com.hung.wallup.databinding.ViewHolderLoadingCollectionBinding
import com.hung.wallup.domain.model.Photo


class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ItemViewHolder>() {
    private companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_NOT_EMPTY = 1
        private var VIEW_TYPE_STATE = VIEW_TYPE_EMPTY
    }

    private var listPhoto = mutableListOf<Photo>()
    private lateinit var context: Context
    private var listener: ((Photo) -> Unit)? = null

    fun setContentList(list: MutableList<Photo>) {
        this.listPhoto = list
        notifyItemRangeChanged(0, list.size)
    }

    fun itemClickListener(l: (Photo) -> Unit) {
        listener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        if (VIEW_TYPE_STATE == VIEW_TYPE_EMPTY) {
            val binding =
                ViewHolderLoadingCollectionBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return EmptyViewHolder(binding)
        } else {
            val binding =
                ItemCollectionPhotoAdapterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (VIEW_TYPE_STATE == VIEW_TYPE_EMPTY) {
            val binding = holder.viewHolder as ViewHolderLoadingCollectionBinding
            binding.view.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.anim_holder_loading
                )
            )
        } else {
            val item: Photo = listPhoto[position]
            val binding = holder.viewHolder as ItemCollectionPhotoAdapterBinding
            Glide.with(context).load(item.coverPhoto).into(binding.ivDetail)
            if (item.isDownloaded) {
                binding.ivDownloaded.visibility = View.VISIBLE
            }
            binding.view.setOnClickListener {
                listener?.let {
                    it(this.listPhoto[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (listPhoto.size == 0) {
            VIEW_TYPE_STATE = VIEW_TYPE_EMPTY
            VIEW_TYPE_STATE
        } else {
            VIEW_TYPE_STATE = VIEW_TYPE_NOT_EMPTY
            VIEW_TYPE_STATE
        }
    }

    open class ItemViewHolder(val viewHolder: ViewBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    class EmptyViewHolder(viewHolder1: ViewBinding) :
        CollectionAdapter.ItemViewHolder(viewHolder1)
}