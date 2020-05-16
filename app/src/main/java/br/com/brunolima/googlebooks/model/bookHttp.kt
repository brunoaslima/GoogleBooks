package br.com.brunolima.googlebooks.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object bookHttp {

    private const val API_KEY = "AIzaSyDzoCagmljfTp2ylJvJZIk24aro-b_t1S4"
    private const val BOOK_JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key=$API_KEY"

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchBook(q: String){
        val request = Request.Builder()
            .url(String.format(BOOK_JSON_URL, q))
            .build()
        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}