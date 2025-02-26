package com.kekyazilim.bookshelf.databaseprocess.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.kekyazilim.bookshelf.databaseprocess.DatabaseQueries
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface BookDao {

    @Query(DatabaseQueries.GET_ALL_BOOKS)
    fun getAllBooks(): Flowable<List<BookTable>>

    @Insert(onConflict = REPLACE)
    fun insert(bookTable: BookTable): Single<Long>

    @Insert(onConflict = REPLACE)
    fun insertAll(bookTableList: List<BookTable>)

    @Update
    fun update(bookTable: BookTable): Completable

    @Delete
    fun delete(bookTable: BookTable): Completable

    @Delete
    fun deleteAll(bookTableList: List<BookTable>): Completable
}