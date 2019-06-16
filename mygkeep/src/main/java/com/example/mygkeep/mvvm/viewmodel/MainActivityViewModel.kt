package com.example.mygkeep.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.NotesRepository
import com.example.mygkeep.mvvm.model.entity.Note

class MainActivityViewModel : BaseViewModel<List<Note>?, MainActivityViewState>() {
    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = MainActivityViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = MainActivityViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = NotesRepository.getNotes()

    init {
        viewStateLiveData.value = MainActivityViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }

    fun viewState(): LiveData<MainActivityViewState> = viewStateLiveData
}