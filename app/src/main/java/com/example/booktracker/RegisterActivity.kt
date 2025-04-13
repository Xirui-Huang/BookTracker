package com.example.booktracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerUsername: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerBirthday: EditText
    private lateinit var genderGroup: RadioGroup
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        registerUsername = findViewById(R.id.registerUsername)
        registerPassword = findViewById(R.id.registerPassword)
        registerBirthday = findViewById(R.id.registerBirthday)
        genderGroup = findViewById(R.id.genderGroup)

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            val username = registerUsername.text.toString()
            val password = registerPassword.text.toString()
            val birthday = registerBirthday.text.toString()

            val selectedGenderId = genderGroup.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            val age = calculateAgeFromBirthday(birthday)

            if (username.isNotEmpty() && password.isNotEmpty() &&
                birthday.isNotEmpty() && gender.isNotEmpty() && age > 0
            ) {
                prefs.edit().apply {
                    putString("username", username)
                    putString("password", password)
                    putString("birthday", birthday)
                    putString("age", age.toString())
                    putString("gender", gender)
                    apply()
                }
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please complete all fields. Format: yyyy-MM-dd", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateAgeFromBirthday(birthday: String): Int {
        return try {
            val parts = birthday.split("-")
            val birthYear = parts[0].toInt()
            val birthMonth = parts[1].toInt()
            val birthDay = parts[2].toInt()

            val today = Calendar.getInstance()
            var age = today.get(Calendar.YEAR) - birthYear

            if (today.get(Calendar.MONTH) + 1 < birthMonth ||
                (today.get(Calendar.MONTH) + 1 == birthMonth && today.get(Calendar.DAY_OF_MONTH) < birthDay)) {
                age--
            }

            age
        } catch (e: Exception) {
            -1
        }
    }
}
