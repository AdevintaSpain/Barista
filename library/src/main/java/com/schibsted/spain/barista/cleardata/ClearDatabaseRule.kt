package com.schibsted.spain.barista.cleardata

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.File

/**
 * This rule clears all app's Databases before running each test
 */
class ClearDatabaseRule(private val databaseOperations: DatabaseOperations = DatabaseOperations()) : TestRule {

    companion object {
        @JvmField
        internal val UNWANTED_FILENAME_SUFFIXES = arrayOf("-journal", "-shm", "-uid", "-wal")
    }

    private var excludeTablesRegex: Regex? = null

    fun excludeTablesMatching(tableNameRegexFilter: String): ClearDatabaseRule {
        excludeTablesRegex = Regex(tableNameRegexFilter)
        return this
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                clearDatabases()
                base.evaluate()
                clearDatabases()
            }
        }
    }

    private fun clearDatabases() {
        with(databaseOperations) {
            getAllDatabaseFiles()
                    .filterNot { hasUnwantedSuffix(it) }
                    .forEach { dbFile ->
                        openDatabase(dbFile)
                                .use { database ->
                                    getTableNames(database)
                                            .filterNot { excludeTablesRegex?.matches(it) ?: false }
                                            .forEach { tableName ->
                                                deleteTableContent(database, tableName)
                                            }
                                }
                    }
        }
    }

    private fun hasUnwantedSuffix(file: File): Boolean {
        return UNWANTED_FILENAME_SUFFIXES.any { file.path.endsWith(it) }
    }

}