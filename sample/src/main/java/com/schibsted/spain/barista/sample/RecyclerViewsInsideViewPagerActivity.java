package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewsInsideViewPagerActivity extends AppCompatActivity {

  private static final int NUM_PAGES = 5;

  private static final String[] FRUITS = {
      "Apple", "Apricot", "Avocado", "Banana", "Bilberry", "Blackberry", "Blackcurrant",
      "Blueberry", "Boysenberry", "Currant", "Cherry", "Cherimoya", "Cloudberry", "Coconut",
      "Cranberry", "Cucumber", "Custardapple", "Damson", "Date", "Dragonfruit", "Durian",
      "Elderberry", "Feijoa", "Fig", "Gojiberry", "Gooseberry", "Grape", "Raisin", "Grapefruit",
      "Guava", "Honeyberry", "Huckleberry", "Jabuticaba", "Jackfruit", "Jambul", "Jujube",
      "Juniperberry", "Kiwifruit", "Kumquat", "Lemon", "Lime", "Loquat", "Longan", "Lychee",
      "Mango", "Marionberry", "Melon", "Cantaloupe", "Honeydew", "Watermelon", "Miraclefruit",
      "Mulberry", "Nectarine", "Nance", "Olive", "Orange", "Bloodorange", "Clementine", "Mandarine",
      "Tangerine", "Papaya", "Passionfruit", "Peach", "Pear", "Persimmon", "Physalis", "Plantain",
      "Plum", "Prune(driedplum)", "Pineapple", "Plumcot(orPluot)", "Pomegranate", "Pomelo",
      "Purplemangosteen", "Quince", "Raspberry", "Salmonberry", "Rambutan", "Redcurrant",
      "Salalberry", "Salak", "Satsuma", "Starfruit", "Solanumquitoense", "Strawberry", "Tamarillo",
      "Tamarind", "Uglifruit", "Yuzu"
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager);

    ViewPager mPager = (ViewPager) findViewById(R.id.pager);
    PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    mPager.setAdapter(mPagerAdapter);
  }

  private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return new ScreenSlidePageFragment();
    }

    @Override
    public int getCount() {
      return NUM_PAGES;
    }
  }

  public static class ScreenSlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      ViewGroup rootView =
          (ViewGroup) inflater.inflate(R.layout.activity_recyclerview, container, false);

      RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
      recyclerView.setHasFixedSize(true);

      LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
      recyclerView.setLayoutManager(mLayoutManager);
      recyclerView.setAdapter(new TextAdapter(getActivity(), FRUITS));

      return rootView;
    }
  }

  static class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private final Activity activity;
    private final String[] items;

    TextAdapter(Activity activity, String[] myDataset) {
      this.activity = activity;
      items = myDataset.clone();
    }

    public TextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View root = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.row_with_buttons, parent, false);
      TextView textView = (TextView) root.findViewById(R.id.textview);
      View yesButton = root.findViewById(R.id.yes);
      View noButton = root.findViewById(R.id.no);
      return new ViewHolder(root, textView, yesButton, noButton);
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.textView.setText(items[position]);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent i = new Intent(activity, LabelActivity.class);
          i.putExtra(LabelActivity.EXTRA_TEXT,
              holder.textView.getText().toString() + " has been clicked");
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
