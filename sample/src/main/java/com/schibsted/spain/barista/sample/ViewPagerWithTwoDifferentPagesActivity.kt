package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerWithTwoDifferentPagesActivity : FragmentActivity() {
  private var pager: ViewPager? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_viewpager)
    val adapter: PagerAdapter = ScreenSlidePagerAdapter(
      supportFragmentManager)
    pager = findViewById<View>(R.id.pager) as ViewPager
    pager!!.adapter = adapter
  }

  override fun onBackPressed() {
    if (pager!!.currentItem == 0) {
      super.onBackPressed()
    } else {
      pager!!.currentItem = pager!!.currentItem - 1
    }
  }

  private class ScreenSlidePagerAdapter internal constructor(fm: FragmentManager?) : FragmentStatePagerAdapter(
    fm!!) {
    override fun getItem(position: Int): Fragment {
      return if (position == 0) {
        ViewPagerFirstFragment()
      } else {
        ViewPagerSecondFragment()
      }
    }

    override fun getCount(): Int {
      return NUM_PAGES
    }
  }

  companion object {
    private const val NUM_PAGES = 2
  }
}