package com.example.personallibraryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.personallibraryapp.data.Book

/**
 * Adapter for displaying the list of books in a RecyclerView.
 */
class BookAdapter(private val onBookClick: (Book) -> Unit) :
    ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view, onBookClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(itemView: View, val onBookClick: (Book) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val yearView: TextView = itemView.findViewById(R.id.textViewYear)
        private val ratingView: TextView = itemView.findViewById(R.id.textViewRating)
        private val statusView: TextView = itemView.findViewById(R.id.textViewStatus)

        fun bind(book: Book) {
            titleView.text = book.title
            yearView.text = "Year: ${book.year ?: "N/A"}"
            ratingView.text = "Rating: ${book.rating}/5"
            statusView.text = if (book.isRead == 1) "Status: Read" else "Status: Unread"

            itemView.setOnClickListener {
                onBookClick(book)
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
    }
}