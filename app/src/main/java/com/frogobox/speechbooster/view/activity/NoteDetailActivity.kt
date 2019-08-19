package com.frogobox.speechbooster.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frogobox.speechbooster.R
import com.frogobox.speechbooster.helper.ConstHelper.Date.DATE_DD_MM_YYYY
import com.frogobox.speechbooster.helper.ConstHelper.Extra.EXTRA_NOTE
import com.frogobox.speechbooster.helper.FunHelper
import com.frogobox.speechbooster.helper.FunHelper.Func.getDateToday
import com.frogobox.speechbooster.model.Note

class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val getIntent = intent.getStringExtra(EXTRA_NOTE)
        val extraNote = FunHelper.ConverterJson.fromJson<Note>(getIntent)

        setTitle(getDateToday(DATE_DD_MM_YYYY))


    }
}
