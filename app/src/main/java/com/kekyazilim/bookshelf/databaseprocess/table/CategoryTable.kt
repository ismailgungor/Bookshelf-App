package com.kekyazilim.bookshelf.databaseprocess.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kekyazilim.bookshelf.databaseprocess.DatabaseKeys
import java.io.Serializable

@Entity(tableName = DatabaseKeys.CATEGORIES)
data class CategoryTable(

    @ColumnInfo(name = DatabaseKeys.CATEGORY_NAME)
    var name: String

) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var categoryId: Int?= null

}