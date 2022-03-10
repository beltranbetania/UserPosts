package com.beltranbetania.userposts.domain.model


import com.beltranbetania.userposts.data.database.entities.UserEntity
import com.beltranbetania.userposts.data.model.UserModel


data class User (val id:Int, val name:String, val email:String, val phone:String, val website:String)

fun UserModel.toDomain() = User(id, name, email,phone, website)

fun UserEntity.toDomain() = User(id, name, email,phone, website)

