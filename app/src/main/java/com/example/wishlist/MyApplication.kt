package com.example.wishlist

import android.app.Application
import com.codepath.articlesearch.AppDatabase

class MyApplication: Application() {
    val db by lazy {AppDatabase.getInstance(this)}
}