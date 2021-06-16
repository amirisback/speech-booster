package com.frogobox.speechbooster.view.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.frogobox.speechbooster.base.view.ui.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentScriptBinding
import com.frogobox.speechbooster.databinding.FragmentVideoBinding
import com.frogobox.speechbooster.view.ui.activity.MainActivity
import com.frogobox.speechbooster.viewmodel.VideoScriptMainViewModel

class VideoFragment : BaseFragment<FragmentVideoBinding>() {

    private lateinit var mViewModel: VideoScriptMainViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainVideoViewModel().apply {

            eventIsEmpty.observe(this@VideoFragment, Observer {
//                setupEventEmptyView(it)
            })

            videoListLive.observe(this@VideoFragment, Observer {

            })

            eventShowProgress.observe(this@VideoFragment, Observer {
//                setupEventProgressView(it)
            })
            
        }
    }
    
}
