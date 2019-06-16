package com.example.mygkeep.mvvm.model.dao.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.dao.RemoteDataDAO
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.model.entity.User
import com.example.mygkeep.mvvm.viewmodel.errors.NoAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth


class FireStoreDAOImpl (private val firebaseAuth: FirebaseAuth, private val store: FirebaseFirestore) : RemoteDataDAO{
    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val notesReference by lazy { store.collection(NOTES_COLLECTION) }

    private val currentUser
        get() = firebaseAuth.currentUser

    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser?.let {
            User(
                it.displayName ?: "",
                it.email ?: ""
            )
        }
    }

    private fun getUserNotesCollection() = currentUser?.let {
        store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()


    override fun subscribeToAllNotes() = MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection()
                .addSnapshotListener { snapshot, e ->
                    value = e?.let { throw it }
                        ?: snapshot?.let {
                            val notes = it.documents.map { it.toObject(Note::class.java) }
                            NoteResult.Success(notes)
                        }
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun saveNote(note: Note): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection().document(note.id)
                .set(note)
                .addOnSuccessListener {
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun getNoteById(id: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection().document(id).get()
                .addOnSuccessListener {
                    value = NoteResult.Success(it.toObject(Note::class.java))
                }.addOnFailureListener {
                    throw it
                }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun deleteNote(id: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        getUserNotesCollection().document(id).delete()
            .addOnSuccessListener {
                value = NoteResult.Success(null)
            }.addOnFailureListener {
                value = NoteResult.Error(it)
            }
    }
}