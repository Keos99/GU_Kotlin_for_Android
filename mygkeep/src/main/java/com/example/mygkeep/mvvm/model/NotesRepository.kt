package com.example.mygkeep.mvvm.model

import com.example.mygkeep.mvvm.model.dao.RemoteDataDAO
import com.example.mygkeep.mvvm.model.dao.impl.FireStoreDAOImpl
import com.example.mygkeep.mvvm.model.entity.Note

object NotesRepository {
    private val remoteProvider : RemoteDataDAO = FireStoreDAOImpl()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}