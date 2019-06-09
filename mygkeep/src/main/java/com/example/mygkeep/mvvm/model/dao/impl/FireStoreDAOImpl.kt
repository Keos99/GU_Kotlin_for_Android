package com.example.mygkeep.mvvm.model.dao.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mygkeep.mvvm.model.NoteResult
import com.example.mygkeep.mvvm.model.dao.RemoteDataDAO
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.model.entity.User
import com.example.mygkeep.mvvm.model.errors.NoAuthException
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth


class FireStoreDAOImpl : RemoteDataDAO {
    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val store by lazy { FirebaseFirestore.getInstance() }
    private val notesReference by lazy { store.collection(NOTES_COLLECTION) }

    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser?.let {
            User(it.displayName ?: "", it.email ?: "")
        }
    }

    private fun getUserNotesCollection() = currentUser?.let {
        store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

    override fun saveNote(note: Note) = MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection().document(note.id)
                .set(note)
                .addOnSuccessListener {
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    OnFailureListener { e ->
                        value = NoteResult.Error(e)
                    }
                }

        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun getNoteById(id: String)= MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection()
                .document(id)
                .get()
                .addOnSuccessListener { snapshot ->
                    value = NoteResult.Success(snapshot.toObject(Note::class.java))
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }

        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }

    override fun subscribeToAllNotes()  = MutableLiveData<NoteResult>().apply {
        try {
            getUserNotesCollection().addSnapshotListener { snapshot, e ->
                value = e?.let {
                    throw it
                } ?: snapshot?.let {
                    val notes = it.documents.map { it.toObject(Note::class.java) }
                    NoteResult.Success(notes)
                }
            }
        } catch (e: Throwable) {
            value = NoteResult.Error(e)
        }
    }
}