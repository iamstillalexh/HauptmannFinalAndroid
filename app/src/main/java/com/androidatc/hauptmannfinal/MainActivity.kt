package com.androidatc.hauptmannfinal

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File

class MainActivity : AppCompatActivity() {

    var photoFile: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camBtn.isEnabled = false
        galBtn.isEnabled = false
        generateBtn.isEnabled = false

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        }
        else
            camBtn.isEnabled = true

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 112)
        }
        else
            galBtn.isEnabled = true

        camBtn.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        }

        galBtn.setOnClickListener {
            var i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            startActivityForResult(i, 102)
        }

        generateBtn.setOnClickListener {
            var intent = Intent(this, SecondaryActivity::class.java)
            intent.putExtra("topText", editTopText.text.toString())
            intent.putExtra("bottomText", editBottomText.text.toString())
            intent.putExtra("pic", photoFile)
            startActivity(intent)
        }

        helpBtn.setOnClickListener {
            var intent = Intent(this, Help::class.java)
            startActivity(intent)
        }
    }

    fun getImageUriFromBitmap (context: Context, bitmap: Bitmap?) : Uri {
        val bytes = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101)
        {
            var pic = data?.getParcelableExtra<Bitmap>("data")
            photoFile = getImageUriFromBitmap(this, pic)
            srcImgView?.setImageBitmap(pic)
        }
        if (requestCode == 102)
        {
            var pic = data?.getData()
            photoFile = pic // in this case pic already is a uri
            srcImgView?.setImageURI(pic)
        }
        if (photoFile != null) generateBtn.isEnabled = true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            camBtn.isEnabled = true
        }
        if (requestCode == 112 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            galBtn.isEnabled = true
        }
    }

}