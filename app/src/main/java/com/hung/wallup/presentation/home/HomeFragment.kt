package com.hung.wallup.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.hung.wallup.databinding.M001HomeFragmentBinding
import com.hung.wallup.domain.model.Photo
import com.hung.wallup.presentation.base.BaseActivity
import com.hung.wallup.presentation.collection.CollectionFragment
import com.hung.wallup.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    companion object {
        val TAG: String = HomeFragment::class.java.name
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val viewModel: HomeViewModel by viewModels()
    private val colorListAdapter: ColorListAdapter = ColorListAdapter()
    private val categoryListAdapter = CatagoryListAdapter()
    private var suggestPhotoListAdapter: SuggestPhotoListAdapter = SuggestPhotoListAdapter()
    private lateinit var binding: M001HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = M001HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.homeState.collect { data ->
                data.suggestPhotos.let {
                    it?.let { suggestPhotoListAdapter.setContentList(it.toMutableList()) }
                    binding.rvSuggest.adapter = suggestPhotoListAdapter
                }

                data.colorList.let {
                    colorListAdapter.setContentList(it.toMutableList())
                    binding.rvColorTone.adapter = colorListAdapter

                }

                data.categories.let {
                    categoryListAdapter.setContentList(it.toMutableList())
                    binding.rvCategories.adapter = categoryListAdapter
                }
            }
        }

        listenEvent()
    }

    private fun listenEvent() {
        colorListAdapter.itemClickListener {
            goToScreenCollection(it.name)
        }

        categoryListAdapter.itemClickListener {
            goToScreenCollection(it.title)
        }

        suggestPhotoListAdapter.itemClickListener {
            goToScreenDetail(it)
        }
    }

    private fun goToScreenDetail(photo: Photo) {
        (activity as BaseActivity).showFragment(
            DetailFragment.TAG,
            DetailFragment.newInstance(photo),
            true
        )

    }

    private fun goToScreenCollection(title: String) {
        (activity as BaseActivity).showFragment(
            CollectionFragment.TAG,
            CollectionFragment.newInstance(title),
            true
        )
    }

}