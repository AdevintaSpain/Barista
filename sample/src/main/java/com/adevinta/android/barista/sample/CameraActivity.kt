package com.adevinta.android.barista.sample

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.adevinta.android.barista.sample.databinding.ActivityCameraBinding
import com.bumptech.glide.Glide
import java.io.File

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    companion object {
        val TAKE_PICTURE_REQUEST_CODE = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.takePicture.setOnClickListener {
            val uri = getPictureUri()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            intent.putExtra("return-data", false)
            startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
            Glide.with(this).load(getPictureUri()).into(binding.imageView)
        }
    }

    private fun getPictureUri(): Uri {
        val path = applicationContext.cacheDir.path + "/test.jpg"
        return FileProvider.getUriForFile(
            this,
            applicationContext.packageName + ".barista.sample.provider",
            File(path)
        )
    }
}
