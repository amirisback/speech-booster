package com.frogobox.speechbooster.mvvm.video


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentVideoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : BaseFragment<FragmentVideoBinding>() {

    private val mViewModel: VideoScriptMainViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel.apply {

            eventEmpty.observe(this@VideoFragment) {

            }

            videoListLive.observe(this@VideoFragment) {

            }

            eventShowProgress.observe(this@VideoFragment) {

            }

        }
    }

    override fun setupOnViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
