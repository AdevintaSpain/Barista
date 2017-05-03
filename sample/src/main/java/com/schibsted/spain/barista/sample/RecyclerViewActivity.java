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

  private static final String[] FRUITS = {
      "Apple", "Apricot", "Avocado", "Banana", "Bilberry", "Blackberry", "Blackcurrant",
      "Blueberry", "Boysenberry", "Currant", "Cherry", "Cherimoya", "Cloudberry", "Coconut",
      "Cranberry", "Cucumber", "Custardapple", "Damson", "Date", "Dragonfruit", "Durian",
      "Elderberry", "Feijoa", "Fig", "Gojiberry", "Gooseberry", "Grape", "Raisin",
      "Grapefruit", "Guava", "Honeyberry", "Huckleberry", "Jabuticaba", "Jackfruit", "Jambul",
      "Jujube", "Juniperberry", "Kiwifruit", "Kumquat", "Lemon", "Lime", "Loquat",
      "Longan", "Lychee", "Mango", "Marionberry", "Melon", "Cantaloupe", "Honeydew",
      "Watermelon", "Miraclefruit", "Mulberry", "Nectarine", "Nance", "Olive", "Orange",
      "Bloodorange", "Clementine", "Mandarine", "Tangerine", "Papaya", "Passionfruit", "Peach",
      "Pear", "Persimmon", "Physalis", "Plantain", "Plum", "Prune(driedplum)", "Pineapple",
      "Plumcot(orPluot)", "Pomegranate", "Pomelo", "Purplemangosteen", "Quince", "Raspberry",
      "Salmonberry", "Rambutan", "Redcurrant", "Salalberry", "Salak", "Satsuma", "Starfruit",
      "Solanumquitoense", "Strawberry", "Tamarillo", "Tamarind", "Uglifruit", "Yuzu"
  };

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
      View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_with_buttons, parent, false);
      TextView textView = (TextView) root.findViewById(R.id.textview);
      View yesButton = root.findViewById(R.id.yes);
      View noButton = root.findViewById(R.id.no);
      return new ViewHolder(root, textView, yesButton, noButton);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.textView.setText(items[position]);
      holder.textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent i = new Intent(activity, LabelActivity.class);
          i.putExtra(LabelActivity.EXTRA_TEXT, ((TextView) view).getText().toString() + " has been clicked");
          activity.startActivity(i);
        }
      });
      holder.yesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent i = new Intent(activity, LabelActivity.class);
          i.putExtra(LabelActivity.EXTRA_TEXT, "'yes' has been clicked");
          activity.startActivity(i);
        }
      });
      holder.noButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent i = new Intent(activity, LabelActivity.class);
          i.putExtra(LabelActivity.EXTRA_TEXT, "'no' has been clicked");
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
}
