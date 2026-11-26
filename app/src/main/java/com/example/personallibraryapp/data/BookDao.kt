package com.example.personallibraryapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for handling Book database operations asynchronously.
 */
@Dao
interface BookDao {
    /** Inserts or replaces a book in the database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    /** Updates an existing book */
    @Update
    suspend fun updateBook(book: Book)

    /** Deletes a book from the database */
    @Delete
    suspend fun deleteBook(book: Book)

    /** Retrieves all books as an observable Flow stream */
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>
}