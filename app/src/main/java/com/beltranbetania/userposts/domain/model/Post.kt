package com.beltranbetania.userposts.domain.model

import com.beltranbetania.userposts.data.database.entities.PostEntity
import com.beltranbetania.userposts.data.model.PostModel

data class Post (val id:Int, val userId:Int, val title:String,val body:String, val isFavorite:Boolean)

fun PostModel.toDomain() = Post(id, userId, title, body, isFavorite)
fun PostEntity.toDomain() = Post(id, userId, title, body, isFavorite)