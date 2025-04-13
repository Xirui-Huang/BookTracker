package com.example.booktracker

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class BookshelfFragment : Fragment() {

    private lateinit var bookListLayout: LinearLayout
    private lateinit var inputTitle: EditText
    private lateinit var addButton: Button
    private val prefKey = "BookshelfPrefs"
    private val favoriteKey = "favorites"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        bookListLayout = view.findViewById(R.id.bookListLayout)
        inputTitle = view.findViewById(R.id.bookInput)
        addButton = view.findViewById(R.id.addBookButton)

        addButton.setOnClickListener {
            val title = inputTitle.text.toString()
            if (title.isNotEmpty()) {
                if (!isAlreadyInShelf(title)) {
                    addBookItem(title)
                    saveBook(title)
                    inputTitle.text.clear()
                } else {
                    Toast.makeText(requireContext(), "该书已在书架中", Toast.LENGTH_SHORT).show()
                }
            }
        }

        loadBooks()

        return view
    }

    private fun addBookItem(title: String) {
        val bookItem = TextView(requireContext()).apply {
            text = title
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black, null))
            setPadding(16, 8, 16, 8)
            setOnLongClickListener {
                bookListLayout.removeView(this)
                removeBook(title)
                true
            }
        }
        bookListLayout.addView(bookItem)
    }

    private fun saveBook(title: String) {
        val prefs = requireContext().getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        val saved = prefs.getStringSet("books", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        saved.add(title)
        prefs.edit().putStringSet("books", saved).apply()
    }

    private fun removeBook(title: String) {
        val prefs = requireContext().getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        val saved = prefs.getStringSet("books", mutableSetOf())?.toMutableSet() ?: return
        saved.remove(title)
        prefs.edit().putStringSet("books", saved).apply()
    }

    private fun loadBooks() {
        val prefs = requireContext().getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        val favPrefs = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val books = prefs.getStringSet("books", mutableSetOf()) ?: setOf()
        val favorites = favPrefs.getStringSet(favoriteKey, mutableSetOf()) ?: setOf()


        val merged = (books + favorites).toSet()
        merged.forEach { addBookItem(it) }


    }

    private fun isAlreadyInShelf(title: String): Boolean {
        val prefs = requireContext().getSharedPreferences(prefKey, Context.MODE_PRIVATE)
        val favPrefs = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val books = prefs.getStringSet("books", mutableSetOf()) ?: setOf()
        val favorites = favPrefs.getStringSet(favoriteKey, mutableSetOf()) ?: setOf()
        return title in books || title in favorites
    }
}
