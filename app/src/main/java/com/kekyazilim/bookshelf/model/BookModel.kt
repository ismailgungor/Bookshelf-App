package com.kekyazilim.bookshelf.model

import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import java.io.Serializable

class BookModel: Serializable {

    var bookId: Int? = null

    var name: String? = null

    var author: String? = null

    var publishDate: Int? = null

    var price: Double? = null

    var rate: Float? = null

    var categoryList: List<CategoryTable>? = null

}