package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.schibsted.spain.barista.sample.ListsActivity.getRecyclerViewTextAt;

public class TextRecyclerViewAdapter extends RecyclerView.Adapter<TextRecyclerViewAdapter.ViewHolder> {
  private final Activity activity;
  private final String[] items;
  private final TextView clickedResult;

  TextRecyclerViewAdapter(Activity activity, String[] items, TextView clickedResult) {
    this.activity = activity;
    this.items = items;
    this.clickedResult = clickedResult;
  }

  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_with_buttons, parent, false);
    TextView textView = (TextView) root.findViewById(R.id.textview);
    View yesButton = root.findViewById(R.id.yes);
    View noButton = root.findViewById(R.id.no);
    return new ViewHolder(root, textView, yesButton, noButton);
  }

  public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.textView.setText(items[position]);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        clickedResult.setText(getRecyclerViewTextAt(position));
      }
    });
    holder.yesButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(activity, LabelActivity.class);
        i.putExtra(LabelActivity.EXTRA_TEXT, "'yes' clicked");
        activity.startActivity(i);
      }
    });
    holder.noButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(activity, LabelActivity.class);
        i.putExtra(LabelActivity.EXTRA_TEXT, "'no' clicked");
        activity.startActivity(i);
      }
    });
  }

  public int getItemCount() {
    return items.length;
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    View yesButton;
    View noButton;

    ViewHolder(View root, TextView textView, View yesButton, View noButton) {
      super(root);
      this.textView = textView;
      this.yesButton = yesButton;
      this.noButton = noButton;
    }
  }
}
