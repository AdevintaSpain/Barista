package com.adevinta.android.barista.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.adevinta.android.barista.sample.ViewPager2Activity.Orientation.HORIZONTAL
import com.adevinta.android.barista.sample.ViewPager2Activity.Orientation.VERTICAL
import com.adevinta.android.barista.sample.databinding.ActivityViewpager2Binding

class ViewPager2Activity : FragmentActivity() {

  private lateinit var binding: ActivityViewpager2Binding

  enum class Orientation {
    VERTICAL,
    HORIZONTAL
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityViewpager2Binding.inflate(layoutInflater)
    setContentView(R.layout.activity_viewpager2)

    binding.viewPager2.adapter = ViewPager2Adapter(activity = this)
  }

  private class ViewPager2Adapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ViewPagerFirstFragment()
      else -> ViewPagerSecondFragment()
    }
  }

  fun set(orientation: Orientation) = runOnUiThread {
    binding.viewPager2.orientation = when (orientation) {
        VERTICAL -> ViewPager2.ORIENTATION_VERTICAL
        HORIZONTAL -> ViewPager2.ORIENTATION_HORIZONTAL
    }
  }
}
