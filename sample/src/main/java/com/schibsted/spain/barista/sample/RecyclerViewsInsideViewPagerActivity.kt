package com.schibsted.spain.barista.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class RecyclerViewsInsideViewPagerActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_viewpager)
    val mPager = findViewById<View>(R.id.pager) as ViewPager
    val mPagerAdapter: PagerAdapter = ScreenSlidePagerAdapter(
      supportFragmentManager)
    mPager.adapter = mPagerAdapter
  }

  private class ScreenSlidePagerAdapter internal constructor(fm: FragmentManager?) : FragmentStatePagerAdapter(
    fm!!) {
    override fun getItem(position: Int): Fragment {
      return ScreenSlidePageFragment()
    }

    override fun getCount(): Int {
      return NUM_PAGES
    }
  }

  class ScreenSlidePageFragment : Fragment() {
    override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
      val rootView = inflater.inflate(R.layout.activity_recyclerview, container, false) as ViewGroup
      val recyclerView = rootView.findViewById<View>(R.id.recycler) as RecyclerView
      recyclerView.setHasFixedSize(true)
      val mLayoutManager = LinearLayoutManager(activity)
      recyclerView.layoutManager = mLayoutManager
      recyclerView.adapter = TextAdapter(activity, FRUITS)
      return rootView
    }
  }

  internal class TextAdapter(private val activity: Activity?, myDataset: Array<String?>) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    private val items: Array<String?>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val root = LayoutInflater.from(parent.context)
        .inflate(R.layout.row_with_buttons, parent, false)
      val textView = root.findViewById<View>(R.id.textview) as TextView
      val yesButton = root.findViewById<View>(R.id.yes)
      val noButton = root.findViewById<View>(R.id.no)
      return ViewHolder(root, textView, yesButton, noButton)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.textView.text = items[position]
      holder.itemView.setOnClickListener {
        val i = Intent(activity, LabelActivity::class.java)
        i.putExtra(LabelActivity.Companion.EXTRA_TEXT,
          holder.textView.text.toString() + " has been clicked")
        activity!!.startActivity(i)
      }
      holder.yesButton.setOnClickListener {
        val i = Intent(activity, LabelActivity::class.java)
        i.putExtra(LabelActivity.Companion.EXTRA_TEXT, "'yes' has been clicked")
        activity!!.startActivity(i)
      }
      holder.noButton.setOnClickListener {
        val i = Intent(activity, LabelActivity::class.java)
        i.putExtra(LabelActivity.Companion.EXTRA_TEXT, "'no' has been clicked")
        activity!!.startActivity(i)
      }
    }

    override fun getItemCount(): Int {
      return items.size
    }

    internal class ViewHolder(root: View?, var textView: TextView, var yesButton: View, var noButton: View) : RecyclerView.ViewHolder(
      root!!)

    init {
      items = myDataset.clone()
    }
  }

  companion object {
    private const val NUM_PAGES = 5
    private val FRUITS = arrayOf<String?>(
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
    )
  }
}