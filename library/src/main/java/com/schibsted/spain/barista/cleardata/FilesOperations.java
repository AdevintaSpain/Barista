package com.schibsted.spain.barista.cleardata;

import android.content.Context;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FilesOperations {

  static void clearAllFiles(Context appContext) {
    for (File file : getFiles(appContext)) {
      removeFile(file);
    }
  }

  private static Collection<File> getFiles(Context appContext) {
    File filesDir = appContext.getFilesDir();
    return listFileTree(filesDir);
  }

  private static Collection<File> listFileTree(File dir) {
    if (dir == null || dir.listFiles() == null) {
      return Collections.emptySet();
    }
    Set<File> fileTree = new HashSet<>();
    for (File entry : dir.listFiles()) {
      if (entry.isFile()) {
        fileTree.add(entry);
      } else {
        fileTree.addAll(listFileTree(entry));
      }
    }
    return fileTree;
  }

  private static void removeFile(File file) {
    file.delete();
  }
}
