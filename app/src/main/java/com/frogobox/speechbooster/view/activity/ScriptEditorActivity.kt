package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_SCRIPT
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_CREATE
import com.frogobox.speechbooster.helper.ConstHelper.Tag.TAG_ACTIVITY_EDIT
import com.frogobox.speechbooster.helper.ConstHelper.TypeData.TYPE_OBJECT
import com.frogobox.speechbooster.model.Script
import com.frogobox.speechbooster.navigation.Navigation.BundleHelper.getBaseBundle
import kotlinx.android.synthetic.main.activity_script_editor.*

class ScriptEditorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_editor)
        setupDetailActivity("")
        setupRole()
    }

    fun setupRole(){

        if (tagOption() == TAG_ACTIVITY_EDIT) {
            setupExtraData()
        } else if (tagOption() == TAG_ACTIVITY_CREATE) {

        }

    }

    fun setupExtraData() {
        val extraData = getBaseBundle<Script>(mActivity, TYPE_OBJECT,  EXTRA_SCRIPT)
        et_script_title.setText(extraData.title)
        et_script_description.setText(extraData.description)
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_accept, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.toolbar_menu_accept -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
