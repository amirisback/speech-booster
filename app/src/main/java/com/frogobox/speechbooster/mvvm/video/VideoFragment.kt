package com.frogobox.speechbooster.mvvm.video


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentVideoBinding
import com.frogobox.speechbooster.mvvm.main.MainActivity

class VideoFragment : BaseFragment<FragmentVideoBinding>() {

    private lateinit var mViewModel: VideoScriptMainViewModel

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainVideoViewModel().apply {

            eventIsEmpty.observe(this@VideoFragment, Observer {

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
