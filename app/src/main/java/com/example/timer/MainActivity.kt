package com.example.timer

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cnt = 0
    var viewCount: Int = 0
    var a = 0
    var b = 0

    val rnb0 = object : Runnable {
        override fun run() {
            cnt += 1
            viewCount = cnt / 10
            a = viewCount / 60
            b = viewCount % 60

            Handler().postDelayed(this, 100)

            if (viewCount < 10) {
                countView.text = "00:0" + viewCount
            } else if (viewCount < 60) {
                countView.text = "00:" + viewCount
            } else if (viewCount < 600) {
                if (b < 10) {
                    countView.text = "0" + a.toString() + ":0" + b
                } else {
                    countView.text = "0" + a.toString() + ":" + b
                }
            }else if (viewCount < 1501){
                if (b < 10) {
                    countView.text = a.toString() + ":0" + b
                } else {
                    countView.text = a.toString() + ":" + b
                }

                if (viewCount == 1500){
                    Log.d("お知らせ","25分経ちました！")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton.setOnClickListener() {
            Handler().post(rnb0)
        }


    }
}