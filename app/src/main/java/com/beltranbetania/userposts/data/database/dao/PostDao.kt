package com.beltranbetania.userposts.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beltranbetania.userposts.data.database.entities.PostEntity
import com.beltranbetania.userposts.data.database.entities.UserEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllUsers(users:List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllPosts(quotes:List<PostEntity>)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers():List<UserEntity>

    @Query("SELECT * FROM user_table WHERE id = :userId")
    suspend fun getUserById(userId: Int):List<UserEntity>

    @Query("SELECT * FROM post_table WHERE userId = :userId")
    suspend fun getPostsByUser(userId: Int):List<PostEntity>




}