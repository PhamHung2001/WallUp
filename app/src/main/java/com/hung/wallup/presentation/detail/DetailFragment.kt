package com.hung.wallup.presentation.detail

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.bumptech.glide.Glide
import com.hung.wallup.R
import com.hung.wallup.databinding.M003DetailPhotoFragmentBinding
import com.hung.wallup.domain.model.Photo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {
        private const val KEY_PHOTO = "KEY_PHOTO"
        private const val CODE_DOWNLOAD = 101
        val TAG: String = DetailFragment::class.java.name

        fun newInstance(photo: Photo): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PHOTO, photo)
                }
            }
        }
    }

    private lateinit var binding: M003DetailPhotoFragmentBinding
    private val viewModel: DetailViewModel by viewModels()
    private var detailPhoto: Photo? = null

    private val onDownloadComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadId == viewModel.mDownloadId) {
                Toast.makeText(context, "Download Complete!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = M003DetailPhotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailPhoto = arguments?.getParcelable(KEY_PHOTO)
        context?.let { Glide.with(it).load(detailPhoto!!.coverPhoto).into(binding.ivPhoto) }
        viewModel.setPhoto(detailPhoto!!)
        viewModel.checkIsFavorite()
        eventClick()
        requireActivity().registerReceiver(
            onDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.detailState.collect { data ->
                data.isStartDownload.apply {
                    if (this) Toast.makeText(context, "Start Download!", Toast.LENGTH_SHORT).show()
                    detailPhoto!!.isDownloaded = true
                }
                data.error?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                data.isFavorite.apply {
                    if (this) {
                        binding.ivBookmark.drawable.level = 1
                    }
                }
            }
        }
    }


    private fun eventClick() {
        binding.ivDownload.setOnClickListener {
            if (!detailPhoto!!.isDownloaded && !viewModel.detailState.value.isSetWallpaper) {
                checkPermissionDownload()
            } else {
                Toast.makeText(
                    context,
                    "You have downloaded this image before!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.ivSetWallpaper.setOnClickListener {
            viewModel.setWallpaper(
                requireContext(),
                binding.ivPhoto.drawable
            )
        }
        binding.ivBookmark.setOnClickListener {
            if (binding.ivBookmark.drawable.level == 0) {
                binding.ivBookmark.startAnimation(
                    AnimationUtils.loadAnimation(
                        context,
                        R.anim.anim_rotate_360
                    )
                )
                binding.ivBookmark.drawable.level = 1
                viewModel.addToFavoriteList()
            } else {
                binding.ivBookmark.startAnimation(
                    AnimationUtils.loadAnimation(
                        context,
                        R.anim.anim_rotate_360
                    )
                )
                binding.ivBookmark.drawable.level = 0
                viewModel.removeFromFavoriteList()
            }

        }
    }

    private fun checkPermissionDownload() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.downloadImage()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE).toTypedArray(), CODE_DOWNLOAD
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CODE_DOWNLOAD) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.downloadImage()
            } else {
                Toast.makeText(
                    context,
                    "You need permission to be able to download images",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().unregisterReceiver(onDownloadComplete)
    }
}
