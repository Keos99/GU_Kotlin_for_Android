package com.example.gu_kotlin_for_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val button: Button = findViewById(R.id.MainButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initUI (){
        button.setOnClickListener {
            doBOOM()
        }
    }

    fun doBOOM (){
        Toast.makeText(this,"BOOOOOOM!!!",Toast.LENGTH_LONG)
    }
}
