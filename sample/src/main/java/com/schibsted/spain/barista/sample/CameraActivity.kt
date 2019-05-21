package com.schibsted.spain.barista.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_camera.image_view
import kotlinx.android.synthetic.main.activity_camera.take_picture
import java.io.File

class CameraActivity : AppCompatActivity() {

  companion object {
    val TAKE_PICTURE_REQUEST_CODE = 42
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_camera)
    take_picture.setOnClickListener {
      val uri = getPictureUri()
      val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
      intent.putExtra("return-data", false)
      startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == TAKE_PICTURE_REQUEST_CODE) {
      Glide.with(this).load(getPictureUri()).into(image_view)
    }
  }

  private fun getPictureUri(): Uri {
    val path = applicationContext.cacheDir.path + "/test.jpg"
    return FileProvider.getUriForFile(this,
        applicationContext.packageName + ".barista.sample.provider",
        File(path))
  }
}
