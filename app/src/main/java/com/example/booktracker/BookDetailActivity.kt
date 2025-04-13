package com.example.booktracker

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.booktracker.utils.FontUtils

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val title = intent.getStringExtra("title") ?: "Unknown title"
        val author = intent.getStringExtra("author") ?: "Unknown author"
        val description = intent.getStringExtra("description") ?: "No brief introduction"

        val titleView = findViewById<TextView>(R.id.detailTitle)
        val authorView = findViewById<TextView>(R.id.detailAuthor)
        val descView = findViewById<TextView>(R.id.detailDescription)

        titleView.text = title
        authorView.text = "Author：$author"
        descView.text = description

        FontUtils.applyFontSize(this, titleView)
        FontUtils.applyFontSize(this, authorView)
        FontUtils.applyFontSize(this, descView)

        val favButton = findViewById<Button>(R.id.favoriteButton)
        favButton.setOnClickListener {
            Toast.makeText(this, "Have already collected \"$title\"", Toast.LENGTH_SHORT).show()
            val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val favs = prefs.getStringSet("favorites", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

            favs.add(title)
            prefs.edit().putStringSet("favorites", favs).apply()

        }
    }
}
