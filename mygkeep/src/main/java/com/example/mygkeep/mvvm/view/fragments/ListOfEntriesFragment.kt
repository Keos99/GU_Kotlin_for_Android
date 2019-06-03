package com.example.mygkeep.mvvm.view.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.view.adapter.MainActivityRVAdapter
import com.example.mygkeep.mvvm.viewmodel.MainActivityViewModel
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
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
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


