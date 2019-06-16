package com.example.mygkeep.mvvm.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.model.entity.Note
import com.example.mygkeep.mvvm.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.coordinator_layout.*
import kotlinx.android.synthetic.main.fragment_note.*
import java.util.*

class NoteFragment (var note: Note?) : Fragment() {

    companion object {
        fun getInstance(note: Note?) : Fragment{
            val instance = NoteFragment(note)
            return instance
        }
    }

    lateinit var viewModel: NoteViewModel

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_note,null)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        toolbar?.title = note?.let {
            it.title
        } ?: let {
            getString(R.string.note_new_note)
        }
        initView()

        return view
    }

    private fun initView() {
        note?.let { note ->
            et_title.setText(note.title)
            et_text.setText(note.text)
            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }
        et_title.addTextChangedListener(textWatcher)
        et_text.addTextChangedListener(textWatcher)
    }

    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return
        note = note?.copy(
            title = et_title.text.toString(),
            text = et_text.text.toString(),
            lastChanged = Date()
        ) ?: createNewNote()

        if (note != null) viewModel.save(note!!)
    }

    fun createNewNote() = Note(UUID.randomUUID().toString(), et_title.text.toString(), et_text.toString())

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            TODO()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}







