package com.frogobox.speechbooster.mvvm.video


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentVideoBinding
import com.frogobox.speechbooster.mvvm.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoFragment : BaseFragment<FragmentVideoBinding>() {

    private val mViewModel: VideoScriptMainViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel.apply {

            eventEmptyData.observe(this@VideoFragment, Observer {

            })

            videoListLive.observe(this@VideoFragment, Observer {

            })

            eventShowProgress.observe(this@VideoFragment, Observer {

            })

        }
    }

    override fun setupUI(savedInstanceState: Bundle?) {

    }

}
