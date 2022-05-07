package com.hung.wallup.presentation.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.hung.wallup.databinding.M002CollectionPhotoFragmentBinding
import com.hung.wallup.presentation.base.BaseActivity
import com.hung.wallup.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    companion object {
        private const val KEY_TITLE = "KEY_TITLE"
        val TAG: String = CollectionFragment::class.java.name

        fun newInstance(title: String): CollectionFragment {
            return CollectionFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, title)
                }
            }
        }
    }

    private lateinit var binding: M002CollectionPhotoFragmentBinding
    private var collectionAdapter = CollectionAdapter()
    private val viewModel: CollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = M002CollectionPhotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = arguments?.getString(KEY_TITLE)

        lifecycle.coroutineScope.launchWhenCreated {
            binding.tvTitle.text = title
            if (title != "Favorite") {
                viewModel.getCollection(title!!)
            } else {
                viewModel.getFavoritePhotos()
            }
            lifecycle.coroutineScope.launchWhenCreated {
                viewModel.collectionState.collect { data ->
                    data.count.let {
                        it?.let { it1 ->
                            binding.tvCount.text = String.format("%s wallpaper available", it1)
                        }
                    }
                    data.photos.let {
                        it?.let { it1 ->
                            collectionAdapter.setContentList(it1.toMutableList())
                            binding.rvListTopicPhoto.adapter = collectionAdapter
                        }

                    }

                }
            }
        }
        listenEvent()
    }

    private fun listenEvent() {
        collectionAdapter.itemClickListener {
            (activity as BaseActivity).showFragment(
                DetailFragment.TAG,
                DetailFragment.newInstance(it),
                true
            )
        }
    }
}