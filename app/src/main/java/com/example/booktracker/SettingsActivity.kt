package com.example.booktracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var fontSizeGroup: RadioGroup
    private lateinit var logoutButton: Button
    private lateinit var switchAccountButton: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        fontSizeGroup = findViewById(R.id.fontSizeGroup)
        logoutButton = findViewById(R.id.logoutButton)
        switchAccountButton = findViewById(R.id.switchAccountButton)


        when (prefs.getString("fontSize", "medium")) {
            "small" -> fontSizeGroup.check(R.id.fontSmall)
            "medium" -> fontSizeGroup.check(R.id.fontMedium)
            "large" -> fontSizeGroup.check(R.id.fontLarge)
        }

        fontSizeGroup.setOnCheckedChangeListener { _, checkedId ->
            val size = when (checkedId) {
                R.id.fontSmall -> "small"
                R.id.fontMedium -> "medium"
                R.id.fontLarge -> "large"
                else -> "medium"
            }
            prefs.edit().putString("fontSize", size).apply()
            Toast.makeText(this, "Font size has been updated", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            prefs.edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        switchAccountButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
