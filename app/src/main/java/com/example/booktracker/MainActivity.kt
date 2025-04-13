package com.example.booktracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)


        replaceFragment(HomeFragment())

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_bookshelf -> replaceFragment(BookshelfFragment())
                R.id.nav_account -> replaceFragment(AccountFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
