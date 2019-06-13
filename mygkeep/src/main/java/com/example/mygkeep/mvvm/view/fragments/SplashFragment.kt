package com.example.mygkeep.mvvm.view.fragments

import android.os.Handler
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.mygkeep.mvvm.viewmodel.SplashViewModel
import com.example.mygkeep.mvvm.viewmodel.SplashViewState


class SplashFragment : BaseFragment<Boolean?, SplashViewState>() {

    companion object {
        private const val START_DELAY = 1000L
    }

    override val viewModel: SplashViewModel by viewModel()

    override val layoutRes: Int? = null

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf{ it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        TODO()
    }
}