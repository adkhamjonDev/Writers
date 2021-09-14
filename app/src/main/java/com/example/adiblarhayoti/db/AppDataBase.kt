package com.example.adiblarhayoti.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AdibUrl::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): AdibDao
    companion object {
        private var appDatabase: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }
}
