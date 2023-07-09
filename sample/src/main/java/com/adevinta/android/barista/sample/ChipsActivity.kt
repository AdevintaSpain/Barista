package com.adevinta.android.barista.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adevinta.android.barista.sample.databinding.ActivityChipsBinding

class ChipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeChip.setOnCloseIconClickListener {
            binding.closeChipText.visibility = View.VISIBLE
        }
    }

}
