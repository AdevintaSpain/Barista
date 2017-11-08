package com.schibsted.spain.barista.rule.cleardata.internal;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileOperations {

  public static void deleteAllFiles(Context appContext) {
    for (File file : getFiles(appContext)) {
      file.delete();
    }
  }

  private static Collection<File> getFiles(Context appContext) {
    File[] androidDirectories = new File[] {
        appContext.getCacheDir(), appContext.getFilesDir()
    };

    List<File> appFiles = new ArrayList<>();
    for (File directory : androidDirectories) {
      appFiles.addAll(listFileTree(directory));
    }
    return appFiles;
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
}
