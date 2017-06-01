package com.schibsted.spain.barista.cleardata;

import android.content.Context;
import java.io.File;

public class FilesOperations {

  static void clearAllFiles(Context appContext) {
    for (File file : getFiles(appContext)) {
      removeFile(file);
    }
  }

  private static File[] getFiles(Context appContext) {
    File filesDir = appContext.getFilesDir();
    return filesDir.listFiles();
  }

  private static void removeFile(File file) {
    file.delete();
  }
}
