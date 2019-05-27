package com.example.mygkeep.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mygkeep.mvvm.model.NotesRepository

class MainActivityViewModel: ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainActivityViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainActivityViewState(NotesRepository.getNotes())
    }

    fun viewState() : LiveData<MainActivityViewState> = viewStateLiveData
}