package com.kekyazilim.bookshelf.model

import com.kekyazilim.bookshelf.databaseprocess.table.BookTable

class CategoryModel {

    var categoryId: Int? = null

    var name: String? = null

    var bookList: List<BookTable>? = null

}