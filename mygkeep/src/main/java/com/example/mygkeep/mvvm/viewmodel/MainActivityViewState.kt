package com.example.mygkeep.mvvm.viewmodel

import com.example.mygkeep.mvvm.model.entity.Note

class MainActivityViewState (val notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)