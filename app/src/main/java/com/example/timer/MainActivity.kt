package com.example.timer

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cnt = 0
    var viewCount :Int = 0
    val hnd0 = Handler()

    val rnb0 = object : Runnable {
        override fun run() {
            cnt += 1
            viewCount = cnt / 10
            hnd0.postDelayed(this, 100)

            if (viewCount < 10) {
                countView.text = "00:0" + viewCount
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton.setOnClickListener() {
            hnd0.post(rnb0)
        }



    }
}