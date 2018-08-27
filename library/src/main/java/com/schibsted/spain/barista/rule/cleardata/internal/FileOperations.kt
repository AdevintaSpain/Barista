package com.schibsted.spain.barista.rule.cleardata.internal

import android.support.test.InstrumentationRegistry.getTargetContext
import java.io.File

class FileOperations {

  private val appContext = getTargetContext()
  private val androidDirectories = arrayOf(appContext.cacheDir, appContext.filesDir)

  fun deleteFile(file: File) {
    file.delete()
  }

  fun getAllFilesRecursively(): Collection<File> {
    return androidDirectories.flatMap { listFileTree(it) }
  }

  private fun listFileTree(dir: File?): Collection<File> {
    if (dir?.listFiles() == null) {
      return emptySet()
    }
    return dir.walkTopDown()
        .filter { it.isFile }
        .toList()
  }
}
