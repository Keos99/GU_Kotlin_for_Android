package com.example.mygkeep.mvvm.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.view.adapter.MainActivityRVAdapter
import com.example.mygkeep.mvvm.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_listofentries.*
import org.jetbrains.anko.support.v4.toast

class ListOfEntriesFragment: Fragment() {
    companion object {
        val instance = ListOfEntriesFragment()
    }

    lateinit var viewModel : MainActivityViewModel
    lateinit var adapter : MainActivityRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_listofentries,null)
        initUI(view)
        viewModel =
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer{ viewState ->
            viewState?.let { adapter.notes = viewState.notes }})
        return view
    }

    fun initUI(view: View) {
        var fab : FloatingActionButton = view.findViewById(R.id.rv_fab_listofentries)
        fab.setOnClickListener { toast("fab is working") }
        var recyclerView : RecyclerView = view.findViewById(R.id.rv_listofentries)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.setHasFixedSize(true)
    }
}


