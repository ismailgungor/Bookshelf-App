package com.kekyazilim.bookshelf.databaseprocess


import androidx.room.Database
import androidx.room.RoomDatabase
import com.kekyazilim.bookshelf.databaseprocess.dao.BookDao
import com.kekyazilim.bookshelf.databaseprocess.dao.CategoryBookJoinDao
import com.kekyazilim.bookshelf.databaseprocess.dao.CategoryDao
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryBookJoinTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable

@Database(entities = [CategoryTable::class, BookTable::class, CategoryBookJoinTable::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun bookDao(): BookDao

    abstract fun categoryBookJoinDao(): CategoryBookJoinDao
}