package com.kekyazilim.bookshelf.util.bookprocess

import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.model.BookModel

/**
 * Responsible to generating book model and book table process
 *
 * @author ismailgungor
 * @since 1.0
 */
class BookTableModelGenerator {


    /**
     * This method generate book table object from book model object
     *
     * @param bookModel
     * @return bookTable as BookTable
     *
     * @author ismailgungor
     * @since 1.0
     */
    fun generateBookTableFromModel(bookModel: BookModel): BookTable {
        val bookTable = BookTable(
            bookModel.name!!,
            bookModel.author!!,
            bookModel.publishDate!!,
            bookModel.price!!,
            bookModel.rate!!
        )

        bookTable.bookId = bookModel.bookId?.let {
            it
        }

        return bookTable
    }

    /**
     * This method generate book model object from given params
     *
     * @param bookId
     * @param name
     * @param author
     * @param rate
     * @param price
     * @param publishYear
     * @param chosenCategories
     *
     * @return bookModel as BookModel
     *
     * @author ismailgungor
     * @since 1.0
     */
    fun generateBookModelWithParams(
        bookId: Int?,
        name: String?,
        author: String?,
        rate: Float?,
        price: String,
        publishYear: String,
        chosenCategories: List<CategoryTable>?
    ): BookModel {

        val bookModel = BookModel()

        bookModel.bookId = bookId
        bookModel.name = name
        bookModel.author = author
        bookModel.rate = rate
        bookModel.price = price.toDouble()
        bookModel.publishDate = publishYear.toInt()
        bookModel.categoryList = chosenCategories

        return bookModel
    }


}