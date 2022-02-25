package com.adevinta.android.barista.sample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.File

class CameraActivity : ComponentActivity() {

    lateinit var button: Button
    lateinit var imageView: ImageView

    private val capturePhoto = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            Glide.with(this).load(getPictureUri()).into(imageView)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                capturePhoto.launch(getPictureUri())
            } else {
                Toast.makeText(
                    this,
                    "You need to enable access to camera from device settings",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        imageView = findViewById(R.id.image_view)
        button = findViewById(R.id.take_picture)

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                capturePhoto.launch(getPictureUri())
            }
            else {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
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
