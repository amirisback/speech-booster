package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.createBundle.baseCreateBundleObject
import com.frogobox.speechbooster.navigation.Navigation.getBundle.baseGetBundleObject
import com.frogobox.speechbooster.navigation.Route.routeImplicit.openRecordActivity
import kotlinx.android.synthetic.main.activity_script_detail.*

class ScriptDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)

        val data = baseGetBundleObject<Script>(mActivity, EXTRA_SCRIPT)
        setupDetailActivity("")

        tv_title_detail.text = data.title
        tv_date_detail.text = data.title
        tv_description_detail.text = data.description

        btn_start_record.setOnClickListener {
            openRecordActivity(this, baseCreateBundleObject(EXTRA_SCRIPT, data))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_edit_delete, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_menu_edit -> {
                true
            }

            R.id.toolbar_menu_delete -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
