package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPager2Activity extends FragmentActivity {

  private static final int NUM_PAGES = 2;

  private ViewPager2 pager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager2);

    FragmentStateAdapter adapter = new ScreenSlidePagerAdapter(this);
    pager = findViewById(R.id.pager);
    pager.setAdapter(adapter);
  }

  @Override
  public void onBackPressed() {
    if (pager.getCurrentItem() == 0) {
      super.onBackPressed();
    } else {
      pager.setCurrentItem(pager.getCurrentItem() - 1);
    }
  }

  private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    ScreenSlidePagerAdapter(FragmentActivity fa) {
      super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      if (position == 0) {
        return new ViewPagerFirstFragment();
      } else {
        return new ViewPagerSecondFragment();
      }
    }

    @Override
    public int getItemCount() {
      return NUM_PAGES;
    }
  }
}
