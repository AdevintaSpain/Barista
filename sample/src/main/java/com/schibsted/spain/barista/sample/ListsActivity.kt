package com.schibsted.spain.barista.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * This Activity helps testing a variety of scenarios with different ListView and RecyclerView.
 * We can dynamically add ListViews or RecyclerViews with intent parameters.
 */
class ListsActivity : AppCompatActivity() {
  private var listsContainer: LinearLayout? = null
  private var clickedResult: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_lists)
    listsContainer = findViewById<View>(R.id.multi_list_container) as LinearLayout
    clickedResult = findViewById<View>(R.id.clicked_text_result) as TextView
    for (id in intent.getIntArrayExtra(EXTRA_SIMPLE_LISTS)!!) {
      addSimpleListView(id)
    }
    for (id in intent.getIntArrayExtra(EXTRA_COMPLEX_LISTS)!!) {
      addComplexListView(id)
    }
    for (id in intent.getIntArrayExtra(EXTRA_RECYCLERS)!!) {
      addRecyclerView(id)
    }
    for (id in intent.getIntArrayExtra(EXTRA_SIMPLE_GRIDS)!!) {
      addSimpleGridView(id)
    }
    for (id in intent.getIntArrayExtra(EXTRA_COMPLEX_GRIDS)!!) {
      addComplexGridView(id)
    }
  }

  private fun addSimpleListView(id: Int) {
    val listView = ListView(this)
    listView.id = id
    listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS)
    listView.onItemClickListener =
      OnItemClickListener { parent, view, position, id -> clickedResult!!.text = getSimpleListViewTextAt(position) }
    addList(listView)
  }

  private fun addComplexListView(id: Int) {
    val listView = ListView(this)
    listView.id = id
    listView.adapter = TextListViewAdapter(this, FRUITS, clickedResult)
    addList(listView)
  }

  private fun addRecyclerView(id: Int) {
    val recyclerView = RecyclerView(this)
    recyclerView.id = id
    recyclerView.setHasFixedSize(true)
    val mLayoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = mLayoutManager
    recyclerView.adapter = TextRecyclerViewAdapter(FRUITS, clickedResult)
    addList(recyclerView)
  }

  private fun addSimpleGridView(id: Int) {
    val gridView = GridView(this)
    gridView.id = id
    gridView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS)
    gridView.onItemClickListener =
      OnItemClickListener { parent, view, position, id -> clickedResult!!.text = getSimpleListViewTextAt(position) }
    addList(gridView)
  }

  private fun addComplexGridView(id: Int) {
    val gridView = GridView(this)
    gridView.id = id
    gridView.adapter = TextListViewAdapter(this, FRUITS, clickedResult)
    addList(gridView)
  }

  private fun addList(listView: View) {
    listsContainer!!.addView(listView,
      LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.MATCH_PARENT,
        1f
      ))
  }

  class IntentBuilder {
    private var simpleListViewIds = intArrayOf()
    private var complexListViewIds = intArrayOf()
    private var recyclerViewIds = intArrayOf()
    private var simpleGridViewIds = intArrayOf()
    private var complexGridViewIds = intArrayOf()
    fun withSimpleLists(vararg ids: Int): IntentBuilder {
      simpleListViewIds = ids
      return this
    }

    fun withComplexLists(vararg ids: Int): IntentBuilder {
      complexListViewIds = ids
      return this
    }

    fun withRecyclers(vararg ids: Int): IntentBuilder {
      recyclerViewIds = ids
      return this
    }

    fun withSimpleGrids(vararg ids: Int): IntentBuilder {
      simpleGridViewIds = ids
      return this
    }

    fun withComplexGrids(vararg ids: Int): IntentBuilder {
      complexGridViewIds = ids
      return this
    }

    fun build(context: Context?): Intent {
      val intent = Intent(context, ListsActivity::class.java)
      intent.putExtra(EXTRA_SIMPLE_LISTS, simpleListViewIds)
      intent.putExtra(EXTRA_COMPLEX_LISTS, complexListViewIds)
      intent.putExtra(EXTRA_RECYCLERS, recyclerViewIds)
      intent.putExtra(EXTRA_SIMPLE_GRIDS, simpleGridViewIds)
      intent.putExtra(EXTRA_COMPLEX_GRIDS, complexGridViewIds)
      return intent
    }
  }

  companion object {
    private const val EXTRA_SIMPLE_LISTS = "simpleLists"
    private const val EXTRA_COMPLEX_LISTS = "complexLists"
    private const val EXTRA_RECYCLERS = "recyclers"
    private const val EXTRA_SIMPLE_GRIDS = "simpleGrids"
    private const val EXTRA_COMPLEX_GRIDS = "complexGrids"
    @kotlin.jvm.JvmField val FRUITS = arrayOf(
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
    )

    @kotlin.jvm.JvmStatic fun getSimpleListViewTextAt(position: Int): String {
      return FRUITS[position] + " simple listview clicked"
    }

    @kotlin.jvm.JvmStatic fun getComplexListViewTextAt(position: Int): String {
      return FRUITS[position] + " complex listview clicked"
    }

    @kotlin.jvm.JvmStatic fun getRecyclerViewTextAt(position: Int): String {
      return FRUITS[position] + " recyclerview clicked"
    }

    @kotlin.jvm.JvmStatic fun buildIntent(): IntentBuilder {
      return IntentBuilder()
    }
  }
}