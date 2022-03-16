package com.androidatc.hauptmannfinal


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val picBuf = intent.getParcelableExtra("pic") as Parcelable?
        val topBuf = intent.getStringExtra("topText")
        val botBuf = intent.getStringExtra("bottomText")

        imageView.setImageURI(intent.getParcelableExtra("pic"))
        topText.text = topBuf
        bottomText.text = botBuf

        if (intent.getStringExtra("textColor") != null) {
            topText.setTextColor(Color.parseColor(intent.getStringExtra("textColor")))
            bottomText.setTextColor(Color.parseColor(intent.getStringExtra("textColor")))
        }

        if (intent.getStringExtra("textSize") != null) {
            topText.textSize = intent.getStringExtra("textSize")!!.toFloat()
            bottomText.textSize = intent.getStringExtra("textSize")!!.toFloat()
        }

        prefsBtn.setOnClickListener {
            val intent = Intent(this, Preferences::class.java)
            intent.putExtra("picBuf", picBuf) // can't think of a better way to do this unfortunately
            intent.putExtra("topBuf", topBuf)
            intent.putExtra("botBuf", botBuf)
            startActivity(intent)
        }
    }
}