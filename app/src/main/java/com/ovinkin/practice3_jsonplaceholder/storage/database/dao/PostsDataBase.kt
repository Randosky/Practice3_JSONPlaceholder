package com.ovinkin.practice3_jsonplaceholder.storage.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ovinkin.practice3_jsonplaceholder.storage.database.entity.PostsEntityDB
import com.ovinkin.practice3_jsonplaceholder.storage.database.utils.Constants

@Database(
    entities = [PostsEntityDB::class], version = Constants.VERSION_DATABASE, exportSchema = false
)
abstract class PostsDataBase : RoomDatabase() {

    abstract fun getDao(): PostsDao

    companion object {
        fun getDatabase(context: Context): PostsDataBase {
            return Room.databaseBuilder(
                context.applicationContext, PostsDataBase::class.java, Constants.NAME_DATABASE
            ).build()
        }
    }
}