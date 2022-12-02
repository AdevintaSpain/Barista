package com.adevinta.android.barista.rule.cleardata

import com.adevinta.android.barista.rule.cleardata.internal.FileOperations
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * This rule clears all app's stored files before running each test
 */
class ClearFilesRule(private val fileOperations: FileOperations = FileOperations()) : TestRule {

  private var includeFilesRegex: Regex? = null

  fun includeFilesMatching(fileNameRegexFilter: String): ClearFilesRule {
    includeFilesRegex = Regex(fileNameRegexFilter)
    return this
  }

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        clearFiles()
        base.evaluate()
        clearFiles()
      }
    }
  }

  private fun clearFiles() {
    fileOperations.getAllFilesRecursively()
        .filter { includeFilesRegex?.matches(it.absolutePath) ?: true }
        .forEach { fileOperations.deleteFile(it) }
  }
}