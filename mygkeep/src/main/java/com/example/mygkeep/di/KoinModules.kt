package com.example.mygkeep.di

import com.example.mygkeep.mvvm.model.NotesRepository
import com.example.mygkeep.mvvm.model.dao.RemoteDataDAO
import com.example.mygkeep.mvvm.model.dao.impl.FireStoreDAOImpl
import com.example.mygkeep.mvvm.viewmodel.ListOfEntriesViewModel
import com.example.mygkeep.mvvm.viewmodel.NoteViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreDAOImpl(get(), get()) } bind RemoteDataDAO::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { ListOfEntriesViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}