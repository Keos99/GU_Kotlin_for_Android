package com.example.mygkeep.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.model.NotesRepository

class AddNoteViewModel : ViewModel() {
    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let { note ->
            NotesRepository.saveNote(note)
        }
    }
}