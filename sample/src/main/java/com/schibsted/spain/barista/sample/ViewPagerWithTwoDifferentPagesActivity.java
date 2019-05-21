package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerWithTwoDifferentPagesActivity extends FragmentActivity {

  private static final int NUM_PAGES = 2;

  private ViewPager pager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpager);

    PagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
    pager = (ViewPager) findViewById(R.id.pager);
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

  private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    ScreenSlidePagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 0) {
        return new ViewPagerFirstFragment();
      } else {
        return new ViewPagerSecondFragment();
      }
    }

    @Override
    public int getCount() {
      return NUM_PAGES;
    }
  }
}
