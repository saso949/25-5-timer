package com.example.timer

import android.graphics.Color
import android.graphics.Color.rgb
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private var soundOne = 0

    var tt = 0
    var cnt = 0
    var viewCount: Int = 0
    var countSee = 0
    var a = 0
    var b = 0
    var studyCount = 1
    var pomoCount = 0


    var rnb0 = object : Runnable {
        override fun run() {

            countSee = viewCount
            cnt += 1
            viewCount = cnt / 10
            a = viewCount / 60
            b = viewCount % 60


            //30分タイマー
            if (studyCount % 5 == 0 && tt > 0) {

                if (countSee < viewCount) {
                    if (situationView.text == "長期休憩中..." || situationView.text == "勉強中") {
                        situationView.text = "長期休憩中"
                    } else if (situationView.text == "長期休憩中") {
                        situationView.text = "長期休憩中."
                    } else if (situationView.text == "長期休憩中.") {
                        situationView.text = "長期休憩中.."
                    } else if (situationView.text == "長期休憩中..") {
                        situationView.text = "長期休憩中..."
                    }
                }

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
                } else if (viewCount < 1801) {
                    if (b < 10) {
                        countView.text = a.toString() + ":0" + b
                    } else {
                        countView.text = a.toString() + ":" + b
                    }

                    if (viewCount == 1800) {
                        Log.d("お知らせ", "30分経ちました！")
                        situationView.text = "長期休憩中"
                        tt += 1
                        cnt = 0
                        viewCount = 0
                        studyCount += 1
                        a = 0
                        b = 0
                        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1.0f)

                        pomoCount ++
                        File(applicationContext.filesDir, "pomoCount.text").writer().use {
                            it.write(pomoCount)
                        }
                        proCount.text = "累計ポモドーロ数:" + pomoCount + "ポモドーロ"
                    }
                }
                //25分タイマー
            } else if (tt % 2 == 0) {
                if (countSee < viewCount) {
                    if (situationView.text == "勉強中..." || situationView.text == "今の状況" || situationView.text == "簡易休憩中" || situationView.text == "長期休憩中") {
                        situationView.text = "勉強中"
                    } else if (situationView.text == "勉強中") {
                        situationView.text = "勉強中."
                    } else if (situationView.text == "勉強中.") {
                        situationView.text = "勉強中.."
                    } else if (situationView.text == "勉強中..") {
                        situationView.text = "勉強中..."
                    }
                }

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
                } else if (viewCount < 1501) {
                    if (b < 10) {
                        countView.text = a.toString() + ":0" + b
                    } else {
                        countView.text = a.toString() + ":" + b
                    }

                    if (viewCount == 1500) {
                        situationView.text = "勉強中"
                        Log.d("お知らせ", "25分経ちました！")
                        tt += 1
                        cnt = 0
                        viewCount = 0
                        a = 0
                        b = 0
                        studyCount += 1
                        countView.text = "00:00"
                        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                }
                //5分タイマー
            } else if (tt % 2 == 1) {

                if (countSee < viewCount) {
                    if (situationView.text == "簡易休憩中..." || situationView.text == "勉強中") {
                        situationView.text = "簡易休憩中"
                    } else if (situationView.text == "簡易休憩中") {
                        situationView.text = "簡易休憩中."
                    } else if (situationView.text == "簡易休憩中.") {
                        situationView.text = "簡易休憩中.."
                    } else if (situationView.text == "簡易休憩中..") {
                        situationView.text = "簡易休憩中..."
                    }
                }

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
                    if (viewCount == 300) {
                        situationView.text = "簡易休憩中"
                        Log.d("お知らせ", "5分がたちました！")
                        tt += 1
                        cnt = 0
                        viewCount = 0
                        a = 0
                        b = 0
                        soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                }
            }

            if (cnt < 18006) {
                Handler().postDelayed(this, 100)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val readFile = File(applicationContext.filesDir, "pomoCount.text")
        if (readFile.exists()) {
            val contents = readFile.bufferedReader().use(BufferedReader::read)
            pomoCount = contents
        }

        proCount.text = "累計ポモドーロ数:" + pomoCount + "ポモドーロ"


        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(2)
            .build()

        // one.wav をロードしておく
        soundOne = soundPool.load(this, R.raw.one, 1)

        startButton.setOnClickListener() {
            startButton.isClickable = false
            Handler().post(rnb0)
        }

        var stopCounter = 0
        var cntBackup = 0
        stopButton.setOnClickListener(){
            if (stopCounter % 2 == 0){
                stopCounter ++
                cntBackup = cnt
                cnt = 18006
                stopButton.text = "再開"
            }else if(stopCounter % 2 == 1){
                stopCounter ++
                cnt = cntBackup
                Handler().post(rnb0)
                stopButton.text = "ストップ"
            }

        }



    }
}