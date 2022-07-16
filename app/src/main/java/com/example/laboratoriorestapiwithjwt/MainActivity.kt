package com.example.laboratoriorestapiwithjwt

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.laboratoriorestapiwithjwt.storage.Storage
import com.example.laboratoriorestapiwithjwt.storage.TOKEN_KEY

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class SharedPreferencesStorage(private val sharedPreferences: SharedPreferences) : Storage {

        override fun saveToken(token: String) {
            sharedPreferences.edit()
                .putString(TOKEN_KEY, token)
                .apply()
        }

        override fun getToken(): String? {
            return sharedPreferences.getString(TOKEN_KEY, "")
        }

        override fun clear() {
            sharedPreferences.edit().clear().apply()
        }

    }

}