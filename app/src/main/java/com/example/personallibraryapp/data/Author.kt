package com.example.personallibraryapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity class representing an Author in the database.
 * Table name: authors with a unique constraint on the name field.
 */
@Entity(
    tableName = "authors",
    indices = [Index(value = ["name"], unique = true)] // Ensures author name is UNIQUE
)
data class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val country: String?
)