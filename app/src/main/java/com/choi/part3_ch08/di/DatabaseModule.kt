package com.choi.part3_ch08.di

import android.content.Context
import androidx.room.Room
import com.choi.part3_ch08.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context,AppDatabase::class.java,"chapter8.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}