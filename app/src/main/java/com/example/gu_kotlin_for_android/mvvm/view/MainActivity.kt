package com.example.gu_kotlin_for_android.mvvm.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.gu_kotlin_for_android.R
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initUI (){
        val button: Button = findViewById(R.id.MainButton)
        button.setOnClickListener {
            doBOOM()
        }
    }

    fun doBOOM (){
        toast("BOOOOOM!!!!!")
    }
}
