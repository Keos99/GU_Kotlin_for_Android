package com.example.mygkeep.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.kotlin_3.data.NotesRepository

class MainActivityViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainActivityViewState> = MutableLiveData()

    init {
        NotesRepository.getNotesLiveData().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it!!) ?: MainActivityViewState(it!!)
        }
    }

    fun viewState() : LiveData<MainActivityViewState> = viewStateLiveData
}