package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chips.closeChip
import kotlinx.android.synthetic.main.activity_chips.closeChipText

class ChipsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chips)

        closeChip.setOnCloseIconClickListener {
            closeChipText.visibility = View.VISIBLE
        }
    }

}
