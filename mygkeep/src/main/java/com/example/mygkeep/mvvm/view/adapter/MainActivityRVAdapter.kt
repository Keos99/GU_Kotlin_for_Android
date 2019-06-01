package com.example.mygkeep.mvvm.view.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mygkeep.R
import com.example.mygkeep.mvvm.model.entity.Note
import kotlinx.android.synthetic.main.item_list_of_entries.view.*

class MainActivityRVAdapter : RecyclerView.Adapter<MainActivityRVAdapter.ViewHolder>() {
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_of_entries,
                parent,
                false
            )
        )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(vh: ViewHolder, pos: Int) = vh.bind(notes[pos])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text

            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }

            setBackgroundColor(ContextCompat.getColor(itemView.context, color))

            setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }
}