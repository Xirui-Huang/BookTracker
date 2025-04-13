package com.example.booktracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import kotlin.random.Random

class AccountFragment : Fragment() {

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val avatarImage = view.findViewById<ImageView>(R.id.avatarImage)
        val usernameText = view.findViewById<TextView>(R.id.usernameText)
        val userIdText = view.findViewById<TextView>(R.id.userIdText)
        val settingsButton = view.findViewById<Button>(R.id.settingsButton)

        prefs = requireContext().getSharedPreferences("UserPrefs", 0)
        val username = prefs.getString("username", "User")
        val userId = prefs.getString("user_id", null) ?: generateUserId()

        usernameText.text = username
        userIdText.text = "ID: $userId"

        settingsButton.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        return view
    }

    private fun generateUserId(): String {
        val id = "USER-" + Random.nextInt(1000, 9999)
        prefs.edit().putString("user_id", id).apply()
        return id
    }
}
