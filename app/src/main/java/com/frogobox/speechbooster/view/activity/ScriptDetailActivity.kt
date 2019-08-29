package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Extra.OPTION_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_INT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.BundleHelper.createBaseBundle
import com.frogobox.speechbooster.navigation.Navigation.BundleHelper.getBaseBundle
import com.frogobox.speechbooster.navigation.Route.routeImplicit.startRecordActivity
import com.frogobox.speechbooster.navigation.Route.routeImplicit.startScriptEditorActivity
import kotlinx.android.synthetic.main.activity_script_detail.*

class ScriptDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_detail)

        val data = getBaseBundle<Script>(TYPE_OBJECT, mActivity, EXTRA_SCRIPT)
        setupDetailActivity("")

        tv_title_detail.text = data.title
        tv_date_detail.text = data.title
        tv_description_detail.text = data.description

        btn_start_record.setOnClickListener {
            startRecordActivity(this, createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val optionToolbar = getBaseBundle<Int>(TYPE_INT, this, OPTION_ACTIVITY_EDIT)
        Log.d("option", optionToolbar.toString())
        if (optionToolbar == TAG_ACTIVITY_EDIT) {
            menuInflater.inflate(R.menu.menu_toolbar_edit_delete, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_menu_edit -> {

                val data = getBaseBundle<Script>(TYPE_OBJECT, mActivity, EXTRA_SCRIPT)
                val extras = createBaseBundle(TYPE_OBJECT, EXTRA_SCRIPT, data)
                val option = createBaseBundle(TYPE_INT, OPTION_ACTIVITY_EDIT, TAG_ACTIVITY_EDIT)
                startScriptEditorActivity(this, extras, option)

                true
            }

            R.id.toolbar_menu_delete -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
