package com.frogobox.speechbooster.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.base.BaseActivity

class ScriptEditorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_script_editor)

        setupDetailActivity("")

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
