package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerButtonFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View root = inflater.inflate(R.layout.activity_centered_button, container, false);
    root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        TextView tv = (TextView) root.findViewById(R.id.textview);
        tv.setText(R.string.click);
      }
    });
    root.findViewById(R.id.really_far_away_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        TextView tv = (TextView) root.findViewById(R.id.textview);
        tv.setText(R.string.click_far_away);
      }
    });

    return root;
  }
}
