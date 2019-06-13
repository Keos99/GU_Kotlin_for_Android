package com.example.mygkeep.mvvm.viewmodel

import com.example.mygkeep.mvvm.model.NotesRepository
import com.example.mygkeep.mvvm.viewmodel.errors.NoAuthException

class SplashViewModel(private val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(authenticated = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}