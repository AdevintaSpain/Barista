package com.schibsted.spain.barista.rule.cleardata

import com.schibsted.spain.barista.rule.cleardata.internal.FileOperations
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.BDDMockito.given
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import java.io.File

class ClearFilesRuleTest {

  @get:Rule
  val mockitoRule = MockitoJUnit.rule()

  val fileOperations = mock(FileOperations::class.java)

  val clearFilesRule = ClearFilesRule(fileOperations)

  @Test
  fun deletesFiles() {
    given(fileOperations.getAllFilesRecursively()).willReturn(listOf(FILE_1, FILE_2))

    executeRule(clearFilesRule)

    verify(fileOperations, atLeastOnce()).deleteFile(FILE_1)
    verify(fileOperations, atLeastOnce()).deleteFile(FILE_2)
  }

  @Test
  fun doesNotDeleteFile_whenNameDoesNotMatchRegex() {
    given(fileOperations.getAllFilesRecursively()).willReturn(listOf(FILE_1, FILE_2))

    executeRule(clearFilesRule.includeFilesMatching("(.+)file1(.+)"))

    verify(fileOperations, atLeastOnce()).deleteFile(FILE_1)
    verify(fileOperations, never()).deleteFile(FILE_2)
  }

  private fun executeRule(rule: TestRule) {
    rule.apply(mock(Statement::class.java), mock(Description::class.java)).evaluate()
  }

  companion object {
    val FILE_1 = File("/path/to/file1.txt")
    val FILE_2 = File("/path/to/file2.txt")
  }
}