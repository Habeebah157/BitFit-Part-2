package com.codepath.articlesearch
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wishlist.FitBitDao
import com.example.wishlist.Fitbitentity

@Database(entities = [Fitbitentity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {



    abstract fun FitBitDao(): FitBitDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Nutrition-db"
            ).build()
    }
}