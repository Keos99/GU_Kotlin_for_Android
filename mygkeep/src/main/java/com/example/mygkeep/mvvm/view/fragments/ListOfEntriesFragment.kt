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
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.view.adapter.MainActivityRVAdapter
import com.example.mygkeep.mvvm.viewmodel.ListOfEntriesViewModel
import com.example.mygkeep.mvvm.viewmodel.ListOfEntriesViewState

class ListOfEntriesFragment: BaseFragment<List<Note>?, ListOfEntriesViewState>() {
    //TODO
    companion object {
        val instance = ListOfEntriesFragment()
    }

    override val layoutRes: Int = R.layout.fragment_listofentries
    override lateinit var viewModel : ListOfEntriesViewModel
    lateinit var adapter : MainActivityRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_listofentries,null)
        initUI(view)
        viewModel =
            ViewModelProviders.of(this).get(ListOfEntriesViewModel::class.java)
        viewModel.viewState().observe(this, Observer{ viewState ->
            viewState?.let { adapter.notes = viewState.notes!! }})
        return view
    }

    fun initUI(view: View) {
        var fab : FloatingActionButton = view.findViewById(R.id.rv_fab_listofentries)
        fab.setOnClickListener {
            changeFragmentTo(NoteFragment.getInstance(null))
        }

        var recyclerView : RecyclerView = view.findViewById(R.id.rv_listofentries)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.setHasFixedSize(true)
    }

    fun changeFragmentTo(newInstance: Fragment){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_master, newInstance)
            ?.addToBackStack("Fragment")?.commit()
    }

    override fun renderData(data: List<Note>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


