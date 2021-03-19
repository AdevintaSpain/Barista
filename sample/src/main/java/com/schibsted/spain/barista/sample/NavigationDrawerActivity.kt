package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_navigation_drawer)
    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    val drawerLayout = findViewById<View>(R.id.drawer) as DrawerLayout
    toolbar.setNavigationIcon(R.drawable.ic_action_menu)
    toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    return false
  }
}