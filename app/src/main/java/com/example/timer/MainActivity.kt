package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cnt = 0
    val hnd0 = Handler()

    val rnb0=object : Runnable{
        override fun run() {
            cnt++
            countView.text=cnt.toString()
            if(cnt<50){
                hnd0.postDelayed(this,100)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener(){
            hnd0.post(rnb0)
        }



    }
}