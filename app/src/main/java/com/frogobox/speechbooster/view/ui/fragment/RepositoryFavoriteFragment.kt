package com.frogobox.speechbooster.view.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.core.BaseListener
import com.frogobox.speechbooster.databinding.FragmentRepositoryFavoriteBinding
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.view.ui.activity.MainActivity
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.view.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.view.adapter.FavoriteScriptAdapter
import com.frogobox.speechbooster.viewmodel.FavoriteScriptMainViewModel

class RepositoryFavoriteFragment : BaseFragment<FragmentRepositoryFavoriteBinding>(),
    BaseListener<FavoriteScript> {

    private lateinit var mViewModel: FavoriteScriptMainViewModel


    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentRepositoryFavoriteBinding {
        return FragmentRepositoryFavoriteBinding.inflate(inflater, container, false)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDataRoomFavorite()
        binding?.ads?.let { setupShowAdsBanner(it.adsBanner) }
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomFavorite()
    }

    override fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainFavoriteViewModel().apply {

            eventIsEmpty.observe(this@RepositoryFavoriteFragment, Observer {
                binding?.empty?.let { it1 -> setupEventEmptyView(it1.emptyView, it) }
            })

            eventShowProgress.observe(this@RepositoryFavoriteFragment, Observer {
                binding?.progress?.let { it1 -> setupEventProgressView(it1.progressView, it) }
            })

            favoriteListLive.observe(this@RepositoryFavoriteFragment, Observer {
                setupRecyclerView(it)
            })

        }
    }

    private fun setupDataRoomFavorite() {
        mViewModel.getFavoriteData()
    }

    private fun setupRecyclerView(data: List<FavoriteScript>) {
        val adapter = FavoriteScriptAdapter()
        context?.let { adapter.setRecyclerViewLayout(it, R.layout.recyclerview_item_script) }
        adapter.setRecyclerViewListener(this)
        adapter.setRecyclerViewData(data)
        binding?.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onItemClicked(data: FavoriteScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
        context?.let { startScriptDetailActivity(it, extras, option) }
    }

    override fun onItemLongClicked(data: FavoriteScript) {
        noAction()
    }


}
