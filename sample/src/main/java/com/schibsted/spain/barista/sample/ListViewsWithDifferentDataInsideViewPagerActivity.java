package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ListViewsWithDifferentDataInsideViewPagerActivity extends AppCompatActivity {

  private static final int NUM_PAGES = 2;

  private static final String[] FRUIT_LIST_ONE = {
      "Apple", "Apricot", "Avocado", "Banana", "Bilberry", "Blackberry", "Blackcurrant",
      "Blueberry", "Boysenberry", "Currant", "Cherry", "Cherimoya", "Cloudberry", "Coconut",
      "Cranberry", "Cucumber", "Custardapple", "Damson", "Date", "Dragonfruit", "Durian",
      "Elderberry", "Feijoa", "Fig", "Gojiberry", "Gooseberry", "Grape", "Raisin", "Grapefruit",
      "Guava", "Honeyberry", "Huckleberry", "Jabuticaba", "Jackfruit", "Jambul", "Jujube",
      "Juniperberry", "Kiwifruit", "Kumquat", "Lemon", "Lime", "Loquat", "Longan", "Lychee"};

  private static final String[] FRUIT_LIST_TWO = {"Mango", "Marionberry", "Melon", "Cantaloupe", "Honeydew", "Watermelon", "Miraclefruit",
      "Mulberry", "Nectarine", "Nance", "Olive", "Orange", "Bloodorange", "Clementine", "Mandarine",
      "Tangerine", "Papaya", "Passionfruit", "Peach", "Pear", "Persimmon", "Physalis", "Plantain",
      "Plum", "Prune(driedplum)", "Pineapple", "Plumcot(orPluot)", "Pomegranate", "Pomelo",
      "Purplemangosteen", "Quince", "Raspberry", "Salmonberry", "Rambutan", "Redcurrant",
      "Salalberry", "Salak", "Satsuma", "Starfruit", "Solanumquitoense", "Strawberry", "Tamarillo",
      "Tamarind", "Uglifruit", "Yuzu"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager);

    ViewPager mPager = findViewById(R.id.pager);
    PagerAdapter mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    mPager.setAdapter(mPagerAdapter);
  }

  private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return new ScreenSlidePageFragment(position);
    }

    @Override
    public int getCount() {
      return NUM_PAGES;
    }
  }

  public static class ScreenSlidePageFragment extends Fragment {

    private int position;

    public ScreenSlidePageFragment(int position) {
      this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_lists, container, false);

      LinearLayout listsContainer = rootView.findViewById(R.id.multi_list_container);

      ListView listView = new ListView(requireContext());
      if (position == 0) {
        listView.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, FRUIT_LIST_ONE));
      } else {
        listView.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, FRUIT_LIST_TWO));
      }
      listView.setId(R.id.listview);
      listsContainer.addView(listView,
          new LinearLayout.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT,
              ViewGroup.LayoutParams.MATCH_PARENT,
              1
          ));

      return rootView;
    }
  }
}