package com.example.mygkeep.mvvm.model.dao

import androidx.lifecycle.LiveData
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.entity.Note

interface RemoteDataDAO {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}