package com.example.mygkeep.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.NotesRepository
import com.example.mygkeep.mvvm.model.entity.Note

class ListOfEntriesViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?, ListOfEntriesViewState>() {
    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = ListOfEntriesViewState(notes = t.data as? List<Note>)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = ListOfEntriesViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = notesRepository.getNotes()

    init {
        viewStateLiveData.value = ListOfEntriesViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }

    fun viewState(): LiveData<ListOfEntriesViewState> = viewStateLiveData
}