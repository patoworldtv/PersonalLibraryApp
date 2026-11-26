package com.example.personallibraryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Room Database class managing connection, version migration, and initial data seeding.
 */
@Database(entities = [Author::class, Book::class], version = 2, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun authorDao(): AuthorDao
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        /** Gets the singleton database instance, creating it if necessary */
        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        /** Migration strategy from version 1 to version 2 (adds is_read column) */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE books ADD COLUMN is_read INTEGER NOT NULL DEFAULT 0")
            }
        }

        /** Callback triggered when the database is first created to seed initial data */
        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateInitialData(database.authorDao(), database.bookDao())
                    }
                }
            }

            /** Seeds at least 3 authors and 4 books into the database */
            private suspend fun populateInitialData(authorDao: AuthorDao, bookDao: BookDao) {
                val author1Id = authorDao.insertAuthor(Author(name = "J.K. Rowling", country = "United Kingdom"))
                val author2Id = authorDao.insertAuthor(Author(name = "George Orwell", country = "United Kingdom"))
                val author3Id = authorDao.insertAuthor(Author(name = "Harper Lee", country = "United States"))

                bookDao.insertBook(Book(title = "Harry Potter and the Sorcerer's Stone", year = 1997, rating = 5, authorId = author1Id, isRead = 1))
                bookDao.insertBook(Book(title = "1984", year = 1949, rating = 5, authorId = author2Id, isRead = 0))
                bookDao.insertBook(Book(title = "Animal Farm", year = 1945, rating = 4, authorId = author2Id, isRead = 1))
                bookDao.insertBook(Book(title = "To Kill a Mockingbird", year = 1960, rating = 5, authorId = author3Id, isRead = 0))
            }
        }
    }
}