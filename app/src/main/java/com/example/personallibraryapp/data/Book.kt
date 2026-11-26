package com.example.personallibraryapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity class representing a Book in the database.
 * Table name: books with a foreign key linking to authors and cascade deletion.
 */
@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = Author::class,
            parentColumns = ["id"],
            childColumns = ["author_id"],
            onDelete = ForeignKey.CASCADE // ON DELETE CASCADE requirement
        )
    ],
    indices = [Index(value = ["author_id"])] // Indexed foreign key requirement
)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val year: Int?,
    val rating: Int, // 1-5 rating requirement
    @ColumnInfo(name = "author_id")
    val authorId: Long,
    @ColumnInfo(name = "is_read", defaultValue = "0")
    val isRead: Int = 0 // v2 adds is_read INTEGER NOT NULL DEFAULT 0
)