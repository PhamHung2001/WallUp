package com.hung.wallup.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hung.wallup.R
import com.hung.wallup.databinding.ItemSuggestImageAdapterBinding
import com.hung.wallup.databinding.ViewHolderLoadingSuggestPhotoBinding
import com.hung.wallup.domain.model.ColorItem
import com.hung.wallup.domain.model.Photo


class SuggestPhotoListAdapter : RecyclerView.Adapter<SuggestPhotoListAdapter.PhotoViewHolder>() {
    private companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_NOT_EMPTY = 1
        private var VIEW_TYPE_STATE = VIEW_TYPE_EMPTY
    }

    private var listSuggetPhoto = mutableListOf<Photo>()
    private lateinit var context: Context
    private var listener :((Photo)->Unit)?=null

    fun setContentList(list: MutableList<Photo>) {
        this.listSuggetPhoto = list
        notifyItemRangeChanged(0, list.size)
    }

    fun itemClickListener(l:(Photo)->Unit){
        listener= l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        context = parent.context
        if (VIEW_TYPE_STATE == VIEW_TYPE_EMPTY) {
            val binding =
                ViewHolderLoadingSuggestPhotoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return EmptyViewHolder(binding)
        } else {
            val binding =
                ItemSuggestImageAdapterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return PhotoViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if (VIEW_TYPE_STATE == VIEW_TYPE_EMPTY) {
            val binding = holder.viewHolder as ViewHolderLoadingSuggestPhotoBinding
            binding.view.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.anim_holder_loading
                )
            )
        } else {
            val item: Photo = listSuggetPhoto[position]
            val binding = holder.viewHolder as ItemSuggestImageAdapterBinding
            Glide.with(context).load(item.coverPhoto).into(binding.ivSuggest)
            if (item.isDownloaded) {
                binding.ivDownloaded.visibility = View.VISIBLE
            }
            binding.view.setOnClickListener {
                listener?.let {
                    it(this.listSuggetPhoto[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (listSuggetPhoto.size == 0) {
            5
        } else {
            listSuggetPhoto.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listSuggetPhoto.size == 0) {
            VIEW_TYPE_STATE = VIEW_TYPE_EMPTY
            return VIEW_TYPE_STATE
        } else {
            VIEW_TYPE_STATE = VIEW_TYPE_NOT_EMPTY
            return VIEW_TYPE_STATE
        }
    }

    open class PhotoViewHolder(val viewHolder: ViewBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    class EmptyViewHolder(viewHolder1: ViewBinding) :
        PhotoViewHolder(viewHolder1)
}