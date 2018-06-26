package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

  private static final List<String> FRUITS = Arrays.asList("Apple", "Apricot", "Avocado", "Banana");

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recyclerview);

    Adapter adapter = new Adapter(FRUITS);

    RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
    recycler.setLayoutManager(new LinearLayoutManager(this));
    recycler.setAdapter(adapter);
  }

  class Adapter extends RecyclerView.Adapter<RecyclerViewActivity.ViewHolder> {

    private List<String> itemList;

    Adapter(List<String> itemList) {
      this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
      return itemList.size();
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    ViewHolder(View root) {
      super(root);
      this.textView = (TextView) root.findViewById(android.R.id.text1);
    }

    public void setText(String text) {
      textView.setText(text);
    }
  }
}
