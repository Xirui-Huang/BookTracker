package com.example.booktracker


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.model.Book
import com.example.booktracker.utils.FontUtils

class BookAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.bookTitle)
        val authorText: TextView = view.findViewById(R.id.bookAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_recommend, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleText.text = book.title
        holder.authorText.text = book.author


        FontUtils.applyFontSize(holder.itemView.context, holder.titleText)
        FontUtils.applyFontSize(holder.itemView.context, holder.authorText)


        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailActivity::class.java).apply {
                putExtra("title", book.title)
                putExtra("author", book.author)
                putExtra("description", book.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = books.size


}

