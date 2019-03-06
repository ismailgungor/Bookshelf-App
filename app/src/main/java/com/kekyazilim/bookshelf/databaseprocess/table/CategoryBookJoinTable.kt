package com.kekyazilim.bookshelf.databaseprocess.table


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.kekyazilim.bookshelf.databaseprocess.DatabaseKeys
import java.io.Serializable

@Entity(
    tableName = DatabaseKeys.CATEGORY_BOOK_JOIN,
    primaryKeys = [DatabaseKeys.CATEGORY_ID, DatabaseKeys.BOOK_ID],
    foreignKeys = [ForeignKey(
        entity = CategoryTable::class,
        parentColumns = [DatabaseKeys.CATEGORY_ID],
        childColumns = [DatabaseKeys.CATEGORY_ID],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = BookTable::class,
        parentColumns = [DatabaseKeys.BOOK_ID],
        childColumns = [DatabaseKeys.BOOK_ID],
        onDelete = ForeignKey.CASCADE
    )]
)

data class CategoryBookJoinTable(
    @ColumnInfo(name = DatabaseKeys.CATEGORY_ID)
    var categoryId: Int,
    @ColumnInfo(name = DatabaseKeys.BOOK_ID)
    var bookId: Int
) : Serializable