package com.example.booktracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            val savedUsername = prefs.getString("username", null)
            val savedPassword = prefs.getString("password", null)

            if (username == savedUsername && password == savedPassword) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.goToRegisterButton).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
