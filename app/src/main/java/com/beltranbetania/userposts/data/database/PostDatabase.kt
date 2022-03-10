package com.beltranbetania.userposts.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.beltranbetania.userposts.data.database.dao.PostDao
import com.beltranbetania.userposts.data.database.entities.PostEntity
import com.beltranbetania.userposts.data.database.entities.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {
    abstract fun getPostDao(): PostDao
}