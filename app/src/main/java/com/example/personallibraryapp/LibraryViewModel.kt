package com.example.personallibraryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.personallibraryapp.data.Author
import com.example.personallibraryapp.data.Book
import com.example.personallibraryapp.data.LibraryDatabase
import com.example.personallibraryapp.data.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel to handle UI data state and off-main-thread database writes.
 */
class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LibraryRepository

    val allBooks: LiveData<List<Book>>
    val allAuthors: LiveData<List<Author>>

    init {
        val authorDao = LibraryDatabase.getDatabase(application).authorDao()
        val bookDao = LibraryDatabase.getDatabase(application).bookDao()
        repository = LibraryRepository(authorDao, bookDao)
        allBooks = repository.allBooks.asLiveData()
        allAuthors = repository.allAuthors.asLiveData()
    }

    /** Inserts a book off the main thread using IO dispatcher */
    fun insert(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertBook(book)
    }

    /** Updates a book off the main thread using IO dispatcher */
    fun update(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateBook(book)
    }

    /** Deletes a book off the main thread using IO dispatcher */
    fun delete(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteBook(book)
    }
}