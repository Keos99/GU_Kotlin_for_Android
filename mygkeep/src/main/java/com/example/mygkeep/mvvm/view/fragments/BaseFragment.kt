package com.example.mygkeep.mvvm.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.viewmodel.BaseViewModel
import com.example.mygkeep.mvvm.viewmodel.BaseViewState
import com.example.mygkeep.mvvm.viewmodel.errors.NoAuthException
import com.firebase.ui.auth.AuthUI
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment<T, S : BaseViewState<T>> : Fragment() {
    //TODO

    companion object {
        private const val RC_SIGN_IN = 4242
    }

    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel.getViewState().observe(this, object : Observer<S> {
            override fun onChanged(t: S?) {
                if (t == null) return
                if (t.error != null) {
                    renderError(t.error)
                    return
                }
                renderData(t.data)
            }
        })
    }

    protected fun renderError(error: Throwable?) {
        when (error) {
            is NoAuthException -> startLogin()
            else -> error?.let {
                it.message?.let { showError(it) }
            }
        }
    }

    private fun startLogin() {
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.android_robot)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            activity?.finish()
        }
    }

    abstract fun renderData(data: T)

    protected fun showError(error: String) {
        toast(error)
    }
}