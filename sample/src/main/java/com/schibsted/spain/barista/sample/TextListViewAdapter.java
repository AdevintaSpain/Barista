package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TextListViewAdapter extends BaseAdapter {
  private Activity activity;
  private String[] items;
  private final TextView clickedResult;

  TextListViewAdapter(Activity activity, String[] items, TextView clickedResult) {
    this.activity = activity;
    this.items = items;
    this.clickedResult = clickedResult;
  }

  @Override
  public int getCount() {
    return items.length;
  }

  @Override
  public Object getItem(int position) {
    return items[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    View rowView = convertView;

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      rowView = inflater.inflate(R.layout.row_with_buttons, parent, false);
    }

    final TextView textView = (TextView) rowView.findViewById(R.id.textview);
    textView.setText(items[position]);

    rowView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clickedResult.setText(ListsActivity.getComplexListViewTextAt(position));
      }
    });

    return rowView;
  }
}
