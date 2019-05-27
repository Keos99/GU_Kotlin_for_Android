package com.example.mygkeep.mvvm.model

import com.example.mygkeep.mvvm.model.entity.Note

object NotesRepository {

    private val notes: List<Note>

    init {
        notes = listOf(
            Note(
                "Первая заметка",
                "Текст первой заметки. Не очень длинный, но очень интересный",
                0xfff06292.toInt()
            ), Note(
                "Вторая заметка",
                "Текст второй заметки. Не очень длинный, но очень интересный",
                0xff9575cd.toInt()
            ), Note(
                "Третья заметка",
                "Текст третьей заметки. Не очень длинный, но очень интересный",
                0xff64b5f6.toInt()
            ), Note(
                "Четвертая заметка",
                "Текст четвертой заметки. Не очень длинный, но очень интересный",
                0xff4db6ac.toInt()
            ), Note(
                "Пятая заметка",
                "Текст пятой заметки. Не очень длинный, но очень интересный",
                0xffb2ff59.toInt()
            ), Note(
                "Шестая заметка",
                "Текст шестой заметки. Не очень длинный, но очень интересный",
                0xffffeb3b.toInt()
            )
        )
    }

    fun getNotes(): List<Note> {
        return notes
    }
}