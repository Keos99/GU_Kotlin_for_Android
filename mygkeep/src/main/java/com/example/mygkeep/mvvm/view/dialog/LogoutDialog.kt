package com.example.mygkeep.mvvm.view.dialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class LogoutDialog : DialogFragment() {

    companion object {
        val TAG = LogoutDialog::class.java.name + "_TAG"
        fun getInstance() = LogoutDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(context)
            .setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти?")
            .setPositiveButton("Да") { dialog, whitch -> (activity as? LogoutListener)?.let { it.onLogout() }}
            .setNegativeButton("Отмена") { dialog, whitch -> dismiss() }
            .create()


    interface LogoutListener {
        fun onLogout()
    }
}