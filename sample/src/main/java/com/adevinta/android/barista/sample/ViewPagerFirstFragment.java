package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ViewPagerFirstFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.activity_centered_label, container, false);
    TextView label = (TextView) view.findViewById(R.id.selected_item);
    label.setText("" + 1);
    return view;
  }
}
