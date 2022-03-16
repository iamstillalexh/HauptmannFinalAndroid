package com.androidatc.hauptmannfinal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_preferences.*
import java.nio.channels.SeekableByteChannel

class Preferences : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val picBuf = intent.getParcelableExtra("picBuf") as Parcelable?
        val topBuf = intent.getStringExtra("topBuf")
        val botBuf = intent.getStringExtra("botBuf")

        redSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                textColorView.setTextColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        greenSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                textColorView.setTextColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        blueSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val colorStr = getColorString()
                textColorView.setTextColor(Color.parseColor(colorStr))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        sizeSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textSizeView.text = sizeSlider.progress.toString()
                textSizeView.textSize = sizeSlider.progress.toFloat()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        applyBtn.setOnClickListener {
            var intent = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("textColor", getColorString())
            intent.putExtra("textSize", sizeSlider.progress.toString())
            intent.putExtra("pic", picBuf)
            intent.putExtra("topText", topBuf)
            intent.putExtra("bottomText", botBuf)
            startActivity(intent)
        }
    }
    fun getColorString(): String {
        var r = Integer.toHexString(((255 * redSlider.progress)/redSlider.max))
        if (r.length==1) r = "0$r"
        var g = Integer.toHexString(((255 * greenSlider.progress)/greenSlider.max))
        if (g.length==1) g = "0$g"
        var b = Integer.toHexString(((255 * blueSlider.progress)/blueSlider.max))
        if (b.length==1) b = "0$b"

        return "#$r$g$b"
    }
}