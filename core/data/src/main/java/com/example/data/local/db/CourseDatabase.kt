package com.example.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.model.CourseModel

@Database(entities = [CourseModel::class], version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun dao(): CourseDao


    companion object {
        private const val DB_NAME = "courses_database"

        private var INSTANCE: CourseDatabase? = null
        private val LOCK = Any()


        fun getInstance(context: Context): CourseDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val database = Room.databaseBuilder(
                    context = context,
                    klass = CourseDatabase::class.java,
                    name = DB_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = database
                return database
            }
        }
    }
}