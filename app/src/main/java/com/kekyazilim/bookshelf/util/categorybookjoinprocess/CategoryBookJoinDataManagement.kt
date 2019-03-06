package com.kekyazilim.bookshelf.util.categorybookjoinprocess

import com.kekyazilim.bookshelf.databaseprocess.IDaoBaseProcess
import com.kekyazilim.bookshelf.databaseprocess.dao.CategoryBookJoinDao
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryBookJoinTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import io.reactivex.Completable
import io.reactivex.Single

class CategoryBookJoinDataManagement(categoryBookJoinDao: CategoryBookJoinDao) : IDaoBaseProcess {

    private val mCategoryBookJoinDao = categoryBookJoinDao

    override fun <T> insertAll(itemList: List<T>): Completable {
        return mCategoryBookJoinDao.insertAll(itemList as List<CategoryBookJoinTable>)
    }

    override fun <T> insert(item: T): Single<Long> {
        return mCategoryBookJoinDao.insert(item as CategoryBookJoinTable)
    }

    override fun <T> update(item: T): Completable? {
        //Silence is golden
        return null
    }

    override fun <T> delete(item: T): Completable? {
        //Silence is golden
        return null
    }

    override fun deleteAll(): Completable? {
        //Silence is golden
        return null
    }

    override fun <T> deleteAll(itemList: List<T>): Completable {
        return mCategoryBookJoinDao.deleteAll(itemList as List<CategoryBookJoinTable>)
    }

    fun getCategoryByBook(bookId: Int): Single<List<CategoryTable>> {
        return this.mCategoryBookJoinDao.getCategoriesForBook(bookId)
    }

    fun deleteItemByBookId(bookId: Int): Completable {
        return Completable.fromAction { mCategoryBookJoinDao.deleteItemByBookId(bookId) }
    }
}