package com.frogobox.speechbooster.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseFragment
import com.frogobox.speechbooster.base.BaseListener
import com.frogobox.speechbooster.util.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.util.helper.ConstHelper.Tag.TAG_ACTIVITY_DETAIL
import com.frogobox.speechbooster.util.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.util.helper.FunHelper.Func.noAction
import com.frogobox.speechbooster.model.FavoriteScript
import com.frogobox.speechbooster.view.activity.MainActivity
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.util.Navigation.BundleHelper.createOptionBundle
import com.frogobox.speechbooster.view.Route.routeImplicit.startScriptDetailActivity
import com.frogobox.speechbooster.view.viewadapter.adapter.FavoriteScriptAdapter
import com.frogobox.speechbooster.viewmodel.FavoriteScriptMainViewModel
import kotlinx.android.synthetic.main.fragment_example_favorite.*

class ExampleFavoriteFragment : BaseFragment(), BaseListener<FavoriteScript> {

    lateinit var mViewModel: FavoriteScriptMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setupViewModel()
        return inflater.inflate(R.layout.fragment_example_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataRoomFavorite()
    }

    override fun onResume() {
        super.onResume()
        setupDataRoomFavorite()
    }

    private fun setupViewModel() {
        mViewModel = (activity as MainActivity).obtainFavoriteViewModel().apply {

            eventIsEmpty.observe(this@ExampleFavoriteFragment, Observer {
                setupEventEmptyView(it)
            })

            eventShowProgress.observe(this@ExampleFavoriteFragment, Observer {
                setupEventProgressView(it)
            })

            favoriteListLive.observe(this@ExampleFavoriteFragment, Observer {
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
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onItemClicked(data: FavoriteScript) {
        val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
        val option = createOptionBundle(TAG_ACTIVITY_DETAIL)
        context?.let { startScriptDetailActivity(it, extras, option) }
    }

    override fun onItemLongClicked(data: FavoriteScript) {
        noAction()
    }

}
