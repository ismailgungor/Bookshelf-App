package com.kekyazilim.bookshelf.util.bookprocess

import com.kekyazilim.bookshelf.databaseprocess.IDaoBaseProcess
import com.kekyazilim.bookshelf.databaseprocess.dao.BookDao
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.model.BookModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class BookDataManagement(bookDao: BookDao, bookTableModelGenerator: BookTableModelGenerator) : IDaoBaseProcess {

    private val mBookDao = bookDao
    private val mBookTableModelGenerator = bookTableModelGenerator

    fun getAllItems(): Flowable<List<BookTable>> {
        return mBookDao.getAllBooks()
    }

    fun getConvertedBookTable(bookModel: BookModel): BookTable {
        return mBookTableModelGenerator.generateBookTableFromModel(bookModel)
    }

    fun getConvertedBookModel(
        bookId: Int?,
        name: String?,
        author: String?,
        rate: Float?,
        price: String,
        publishYear: String,
        chosenCategories: List<CategoryTable>?
    ): BookModel {
        return mBookTableModelGenerator.generateBookModelWithParams(
            bookId,
            name,
            author,
            rate,
            price,
            publishYear,
            chosenCategories
        )
    }

    override fun <T> insert(item: T): Single<Long> {
        return mBookDao.insert(item as BookTable)
    }

    override fun <T> insertAll(itemList: List<T>): Completable? {
        return Completable.fromAction {
            mBookDao.insertAll(itemList as List<BookTable>)
        }
    }

    override fun <T> update(item: T): Completable {
        return mBookDao.update(item as BookTable)
    }

    override fun <T> delete(item: T): Completable {
        return mBookDao.delete(item as BookTable)
    }

    override fun <T> deleteAll(itemList: List<T>): Completable {
        return mBookDao.deleteAll(itemList as List<BookTable>)
    }

    override fun deleteAll(): Completable? {
        //Silence is golden
        return null
    }
}