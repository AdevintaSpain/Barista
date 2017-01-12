package com.schibsted.spain.barista.sample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_date_picker);

    findViewById(R.id.launch_date_picker).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
      }
    });
  }

  public static class DatePickerFragment extends DialogFragment
      implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      // Use the current date as the default date in the picker
      final Calendar c = Calendar.getInstance();
      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH);
      int day = c.get(Calendar.DAY_OF_MONTH);

      // Create a new instance of DatePickerDialog and return it
      return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
      TextView result = (TextView) getActivity().findViewById(R.id.date_picker_result);
      result.setText(year + "+" + month + "+" + day);
    }
  }
}

