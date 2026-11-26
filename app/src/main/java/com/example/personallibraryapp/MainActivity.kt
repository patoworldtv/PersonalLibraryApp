package com.example.personallibraryapp

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personallibraryapp.data.Book
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Main Activity displaying the book list, supporting adding and editing books.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: LibraryViewModel by viewModels()
    private lateinit var settingsManager: SettingsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settingsManager = SettingsManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewBooks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter with click listener for editing books
        val adapter = BookAdapter { book ->
            showEditBookDialog(book)
        }
        recyclerView.adapter = adapter

        // Observe LiveData from ViewModel (updates automatically when DB changes)
        viewModel.allBooks.observe(this) { books ->
            adapter.submitList(books)
        }

        // Floating action button to add a new book
        val fab = findViewById<FloatingActionButton>(R.id.fabAddBook)
        fab.setOnClickListener {
            showAddBookDialog()
        }
    }

    /**
     * Dialog to add a new book. Defaults to authorId = 1 (seeded author).
     */
    private fun showAddBookDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add New Book")

        val input = EditText(this)
        input.hint = "Book Title"
        builder.setView(input)

        builder.setPositiveButton("Add") { _, _ ->
            val title = input.text.toString().trim()
            if (title.isNotEmpty()) {
                val newBook = Book(title = title, year = 2026, rating = 5, authorId = 1L, isRead = 0)
                viewModel.insert(newBook)
                Toast.makeText(this, "Book added!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }

    /**
     * Dialog to edit an existing book.
     */
    private fun showEditBookDialog(book: Book) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Book")

        val input = EditText(this)
        input.setText(book.title)
        builder.setView(input)

        builder.setPositiveButton("Update") { _, _ ->
            val updatedTitle = input.text.toString().trim()
            if (updatedTitle.isNotEmpty()) {
                val updatedBook = book.copy(title = updatedTitle, isRead = 1)
                viewModel.update(updatedBook)
                Toast.makeText(this, "Book updated!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNeutralButton("Delete") { _, _ ->
            viewModel.delete(book)
            Toast.makeText(this, "Book deleted!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}