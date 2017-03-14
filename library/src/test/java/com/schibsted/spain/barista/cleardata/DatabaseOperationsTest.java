package com.schibsted.spain.barista.cleardata;

import java.io.File;
import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseOperationsTest {

  @Test
  public void filtersUnwantedDatabaseFiles() throws Exception {
    List<File> allFiles = asList(
        new File("my_database.db"),
        new File("my_database.db-journal"),
        new File("my_database.db-shm"),
        new File("my_database.db-uid"),
        new File("my_database.db-wal"),
        new File("another_database")
    );
    List<File> expectedFiles = asList(
        new File("my_database.db"),
        new File("another_database")
    );

    List<File> filteredFiles = DatabaseOperations.filterUnwantedDatabaseFiles(allFiles);

    assertThat(filteredFiles).containsExactlyElementsOf(expectedFiles);
  }
}