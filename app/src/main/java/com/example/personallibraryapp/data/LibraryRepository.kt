package com.example.personallibraryapp.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository class that abstracts access to the data sources (DAOs).
 */
class LibraryRepository(private val authorDao: AuthorDao, private val bookDao: BookDao) {
    val allAuthors: Flow<List<Author>> = authorDao.getAllAuthors()
    val allBooks: Flow<List<Book>> = bookDao.getAllBooks()

    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}