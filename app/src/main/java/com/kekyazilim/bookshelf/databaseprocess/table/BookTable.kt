package com.kekyazilim.bookshelf.databaseprocess.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kekyazilim.bookshelf.databaseprocess.DatabaseKeys
import java.io.Serializable

@Entity(
    tableName = DatabaseKeys.BOOKS
)
data class BookTable(

    @ColumnInfo(name = DatabaseKeys.BOOK_NAME)
    var name: String,

    @ColumnInfo(name = DatabaseKeys.BOOK_AUTHOR)
    var author: String,

    @ColumnInfo(name = DatabaseKeys.BOOK_PUBLISH_DATE)
    var publishDate: Int,

    @ColumnInfo(name = DatabaseKeys.BOOK_PRICE)
    var price: Double,

    @ColumnInfo(name = DatabaseKeys.BOOK_RATE)
    var rate: Float

    ) : Serializable {
    @PrimaryKey
    var bookId: Int? =null
}