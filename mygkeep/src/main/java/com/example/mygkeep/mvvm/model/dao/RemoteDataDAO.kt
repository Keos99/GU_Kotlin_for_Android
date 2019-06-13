package com.example.mygkeep.mvvm.model.dao

import androidx.lifecycle.LiveData
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.model.entity.User

interface RemoteDataDAO {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(id: String): LiveData<NoteResult>
}