package com.example.personallibraryapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for handling Author database operations asynchronously.
 */
@Dao
interface AuthorDao {
    /** Inserts a new author and returns their generated row ID */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAuthor(author: Author): Long

    /** Retrieves all authors as an observable Flow stream */
    @Query("SELECT * FROM authors")
    fun getAllAuthors(): Flow<List<Author>>
}