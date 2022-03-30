package com.frogobox.speechbooster.mvvm.repository


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.IFrogoBindingAdapter
import com.frogobox.speechbooster.core.BaseFragment
import com.frogobox.speechbooster.databinding.FragmentRepositoryFavoriteBinding
import com.frogobox.speechbooster.databinding.RecyclerviewItemScriptBinding
import com.frogobox.speechbooster.mvvm.favorite.FavoriteScriptMainViewModel
import com.frogobox.speechbooster.route.Implicit.Activity.startScriptDetailActivity
import com.frogobox.speechbooster.source.model.FavoriteScript
import com.frogobox.speechbooster.util.ConstHelper.Extra.EXTRA_FAVORITE_SCRIPT
import com.frogobox.speechbooster.util.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryFavoriteFragment : BaseFragment<FragmentRepositoryFavoriteBinding>() {

    private val mViewModel: FavoriteScriptMainViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRepositoryFavoriteBinding {
        return FragmentRepositoryFavoriteBinding.inflate(inflater, container, false)
    }

    override fun setupOnViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDataRoomFavorite()
        setupShowAdsBanner(binding.ads.adsBanner)
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomFavorite()
    }

    override fun setupViewModel() {
        mViewModel.apply {

            eventEmpty.observe(viewLifecycleOwner) {
                setupEmptyView(binding.empty.emptyView, it)
            }

            eventShowProgress.observe(viewLifecycleOwner) {
                setupProgressView(binding.progress.progressView, it)
            }

            favoriteListLive.observe(viewLifecycleOwner) {
                setupRecyclerView(it)
            }

        }
    }

    private fun setupDataRoomFavorite() {
        mViewModel.getFavoriteData()
    }

    private fun setupRecyclerView(data: List<FavoriteScript>) {

        val adapterCallback =
            object : IFrogoBindingAdapter<FavoriteScript, RecyclerviewItemScriptBinding> {
                override fun onItemClicked(
                    binding: RecyclerviewItemScriptBinding,
                    data: FavoriteScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<FavoriteScript>
                ) {
                    val extras = createBaseBundle(TYPE_OBJECT, EXTRA_FAVORITE_SCRIPT, data)
                    val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
                    context?.let { startScriptDetailActivity(it, extras, option) }
                }

                override fun onItemLongClicked(
                    binding: RecyclerviewItemScriptBinding,
                    data: FavoriteScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<FavoriteScript>
                ) {

                }

                override fun setViewBinding(parent: ViewGroup): RecyclerviewItemScriptBinding {
                    return RecyclerviewItemScriptBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                }

                override fun setupInitComponent(
                    binding: RecyclerviewItemScriptBinding,
                    data: FavoriteScript,
                    position: Int,
                    notifyListener: FrogoRecyclerNotifyListener<FavoriteScript>
                ) {
                    binding.apply {
                        tvTitle.text = data.title
                        tvDescription.text = data.description
                        tvDate.text = data.dateTime
                    }
                }
            }

        binding.recyclerView.injectorBinding<FavoriteScript, RecyclerviewItemScriptBinding>()
            .addData(data)
            .addCallback(adapterCallback)
            .createLayoutStaggeredGrid(2)
            .build()


    }

}