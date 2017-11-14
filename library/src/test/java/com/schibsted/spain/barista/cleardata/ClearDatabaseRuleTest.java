package com.schibsted.spain.barista.cleardata;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.List;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
public class ClearDatabaseRuleTest {

  private static final File DB_1_FILE = new File("db1.db");
  private static final File DB_2_FILE = new File("db2.db");
  private static final SQLiteDatabase DB_1 = mock(SQLiteDatabase.class);
  private static final SQLiteDatabase DB_2 = mock(SQLiteDatabase.class);
  private static final List<String> DB_1_TABLES = asList("db1_table1", "db1_table2");
  private static final List<String> DB_2_TABLES = asList("db2_table1", "db2_table2");

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  DatabaseOperations operations;
  @Mock
  Statement dummyStatement;
  @Mock
  Description dummyDescription;

  @Before
  public void setUp() throws Exception {
    given(operations.openDatabase(DB_1_FILE)).willReturn(DB_1);
    given(operations.openDatabase(DB_2_FILE)).willReturn(DB_2);
    given(operations.getTableNames(DB_1)).willReturn(DB_1_TABLES);
    given(operations.getTableNames(DB_2)).willReturn(DB_2_TABLES);
  }

  @Test
  public void deleteMultipleDatabases() throws Throwable {
    given(operations.getAllDatabaseFiles()).willReturn(asList(DB_1_FILE, DB_2_FILE));

    executeRule(new ClearDatabaseRule(operations));

    verify(operations, atLeastOnce()).deleteTableContent(eq(DB_1), anyString());
    verify(operations, atLeastOnce()).deleteTableContent(eq(DB_2), anyString());
  }

  @Test
  public void deleteAllTablesFromDatabase() throws Throwable {
    given(operations.getAllDatabaseFiles()).willReturn(asList(DB_1_FILE, DB_2_FILE));

    executeRule(new ClearDatabaseRule(operations));

    verify(operations, atLeastOnce()).deleteTableContent(DB_1, DB_1_TABLES.get(0));
    verify(operations, atLeastOnce()).deleteTableContent(DB_1, DB_1_TABLES.get(1));
    verify(operations, atLeastOnce()).deleteTableContent(DB_2, DB_2_TABLES.get(0));
    verify(operations, atLeastOnce()).deleteTableContent(DB_2, DB_2_TABLES.get(1));
  }

  @Test
  @Parameters(method = "unwantedSuffixesParameters")
  public void doesNotDeleteFilesWithUnwantedSuffixes(String suffix) throws Throwable {
    File unwantedFile = new File("unwanted.db" + suffix);
    given(operations.getAllDatabaseFiles()).willReturn(singletonList(unwantedFile));

    executeRule(new ClearDatabaseRule(operations));

    verify(operations, never()).deleteTableContent(any(SQLiteDatabase.class), anyString());
  }

  private String[] unwantedSuffixesParameters() {
    // Don't understand this method? Check out https://github.com/Pragmatists/JUnitParams
    return ClearDatabaseRule.UNWANTED_FILENAME_SUFFIXES;
  }

  @Test
  public void closesDatabase() throws Throwable {
    given(operations.getAllDatabaseFiles()).willReturn(singletonList(DB_1_FILE));

    executeRule(new ClearDatabaseRule(operations));

    verify(DB_1, atLeastOnce()).close();
  }

  @Test
  public void doesNotDeleteTable_whenNameMatchesRegex() throws Throwable {
    given(operations.getAllDatabaseFiles()).willReturn(singletonList(DB_1_FILE));
    given(operations.getTableNames(DB_1)).willReturn(asList("some_table", "excluded_table"));
    String excludedRegex = "excluded_.+";

    ClearDatabaseRule rule = new ClearDatabaseRule(operations)
        .excludeTablesMatching(excludedRegex);
    executeRule(rule);

    verify(operations, never()).deleteTableContent(DB_1, "excluded_table");
    verify(operations, atLeastOnce()).deleteTableContent(DB_1, "some_table");
  }

  private void executeRule(ClearDatabaseRule rule) throws Throwable {
    rule.apply(dummyStatement, dummyDescription).evaluate();
  }
}
