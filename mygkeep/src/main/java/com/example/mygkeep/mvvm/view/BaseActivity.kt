package com.example.mygkeep.mvvm.view

import androidx.fragment.app.Fragment
import com.example.mygkeep.mvvm.viewmodel.BaseViewState

abstract class BaseActivity<T, S : BaseViewState<T>> : Fragment() {

}