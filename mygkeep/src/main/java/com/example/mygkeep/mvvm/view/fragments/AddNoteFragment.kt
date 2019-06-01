package com.example.mygkeep.mvvm.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.viewmodel.AddNoteViewModel
import java.util.*

class AddNoteFragment : Fragment() {
    companion object {
        val instance = AddNoteFragment()
    }
    private var note: Note? = null
    lateinit var viewModel: AddNoteViewModel
    lateinit var editText: EditText
    lateinit var textInputEditText: TextInputEditText

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_addnote,null)
        initUI()
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
        note = TODO()

        return view
    }

    fun initUI (view: View) {
        editText = view.findViewById(R.id.et_text)
        textInputEditText = view.findViewById(R.id.et_title)
    }

    private fun initView() {
        note?.let { note ->
            textInputEditText.setText(note.title)
            editText.setText(note.text)
            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }
        }
        textInputEditText.addTextChangedListener(textWatcher)
        editText.addTextChangedListener(textWatcher)
    }

    private fun saveNote() {
        if (textInputEditText.text == null || textInputEditText.text!!.length < 3) return
        note = note?.copy(
            title = textInputEditText.text.toString(),
            text = editText.text.toString(),
            lastChanged = Date()
        ) ?: createNewNote()

        if (note != null) viewModel.save(note!!)
    }

    fun createNewNote() = Note(UUID.randomUUID().toString(), textInputEditText.text.toString(), editText.toString())

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            TODO()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}







