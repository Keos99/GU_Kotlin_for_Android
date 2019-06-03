package com.example.mygkeep.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.view.fragments.ListOfEntriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.coordinator_layout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
    }

    fun initUi(){
        var toggle = ActionBarDrawerToggle(this, drawer_layout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        changeFragmentTo(ListOfEntriesFragment.instance)
        setSupportActionBar(toolbar)
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
