package com.example.laboratoriorestapiwithjwt.storage

interface Storage {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clear()

}