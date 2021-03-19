package com.schibsted.spain.barista.sample

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class PickersActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_pickers)
    findViewById<View>(R.id.launch_date_picker).setOnClickListener {
      val newFragment: DialogFragment = DatePickerFragment()
      newFragment.show(supportFragmentManager, "datePicker")
    }
    findViewById<View>(R.id.launch_time_picker).setOnClickListener {
      val fragment = TimePickerFragment()
      fragment.show(supportFragmentManager, "timePicker")
    }
  }

  class DatePickerFragment : DialogFragment(), OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      // Use the current date as the default date in the picker
      val c = Calendar.getInstance()
      val year = c[Calendar.YEAR]
      val month = c[Calendar.MONTH]
      val day = c[Calendar.DAY_OF_MONTH]

      // Create a new instance of DatePickerDialog and return it
      return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
      val result = activity!!.findViewById<View>(R.id.date_picker_result) as TextView
      result.text = "$year+$month+$day"
    }
  }

  class TimePickerFragment : DialogFragment(), OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val calendar = Calendar.getInstance()
      val hour = calendar[Calendar.HOUR_OF_DAY]
      val minute = calendar[Calendar.MINUTE]
      return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(timePicker: TimePicker, hour: Int, minute: Int) {
      val result = activity!!.findViewById<View>(R.id.date_picker_result) as TextView
      result.text = String.format("%d:%d", hour, minute)
    }
  }
}