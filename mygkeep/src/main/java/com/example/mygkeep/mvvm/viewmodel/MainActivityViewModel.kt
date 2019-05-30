package com.example.mygkeep.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import ru.geekbrains.kotlin_3.data.NotesRepository

class MainActivityViewModel: ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainActivityViewState> = MutableLiveData()

    init {
        NotesRepository.getNotesLiveData().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it!!) ?: MainActivityViewState(it!!)
        }
    }

    fun viewState() : LiveData<MainActivityViewState> = viewStateLiveData
}