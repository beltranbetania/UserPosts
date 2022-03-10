package com.beltranbetania.userposts.data.di

import android.content.Context
import androidx.room.Room
import com.beltranbetania.userposts.core.POST_DATABASE_NAME
import com.beltranbetania.userposts.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java, POST_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: PostDatabase) = db.getPostDao()
}