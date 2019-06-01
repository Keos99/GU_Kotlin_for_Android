package com.example.mygkeep.mvvm.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.view.fragments.ListOfEntriesFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    fun initUi(){
        drawerLayout = findViewById(R.id.drawer_layout)
        var toggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var navigation: NavigationView = findViewById(R.id.nav_view)
        navigation.setNavigationItemSelectedListener(this)
        changeFragmentTo(ListOfEntriesFragment.instance)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented")
    }

    fun changeFragmentTo(newInstance: Fragment){
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.fl_master, newInstance)
            .addToBackStack("Fragment").commit()
    }
}
