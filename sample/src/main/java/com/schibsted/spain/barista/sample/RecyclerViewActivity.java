package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewActivity extends AppCompatActivity {

  private static final String[] FRUITS = { "Banana", "Apple", "Orange", "Raspberry" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recyclerview);

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setHasFixedSize(true);

    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(new TextAdapter(this, FRUITS));
  }

  public static class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private final Activity activity;
    private final String[] items;

    TextAdapter(Activity activity, String[] myDataset) {
      this.activity = activity;
      items = myDataset.clone();
    }

    public TextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_textview, parent, false);
      return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.textView.setText(items[position]);
      holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent i = new Intent(activity, LabelActivity.class);
          i.putExtra(LabelActivity.EXTRA_TEXT, ((TextView) view).getText().toString());
          activity.startActivity(i);
        }
      });
    }

    public int getItemCount() {
      return items.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
      TextView textView;

      ViewHolder(TextView v) {
        super(v);
        textView = v;
      }
    }
  }
}
