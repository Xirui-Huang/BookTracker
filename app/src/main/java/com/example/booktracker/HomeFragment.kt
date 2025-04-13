package com.example.booktracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booktracker.model.Book

class HomeFragment : Fragment() {

    private lateinit var bookList: List<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recommendedBooksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 示例推荐书籍
        bookList = listOf(
            Book("The Kite Runner", "Khaled Hosseini"),
            Book("One Hundred Years of Solitude", "Garcia Marquez"),
            Book("Besiege a City", "Qian Zhongshu"),
            Book("Brief History of Mankind", "Yuval Harari")
        )

        recyclerView.adapter = BookAdapter(bookList)

        return view
    }
}
