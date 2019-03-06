package com.kekyazilim.bookshelf.util.categoryprocess

import com.kekyazilim.bookshelf.databaseprocess.IDaoBaseProcess
import com.kekyazilim.bookshelf.databaseprocess.dao.CategoryDao
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import io.reactivex.Completable
import io.reactivex.Single

class CategoryDataManagement(
    categoryDao: CategoryDao,
    categoryFileManager: CategoryFileManager,
    categoryTypeConverter: CategoryTypeConverter
) : IDaoBaseProcess {


    private val mCategoryDao = categoryDao
    private val mCategoryFileManager = categoryFileManager
    private val mCategoryTypeConverter = categoryTypeConverter

    fun getAllICategoriesFromDB(): Single<List<CategoryTable>> {
        return mCategoryDao.getAllCategories()
    }

    fun getCategoryCountFromDB(): Single<Int> {
        return mCategoryDao.getCategoryCount()
    }

    fun getAllCategoriesFromFile(): ArrayList<CategoryTable>? {

        this.mCategoryFileManager.loadStringCategoryNameArrayFromAsset()?.let {
            return this.mCategoryTypeConverter.getConvertedCategoryListFromJsonArray(it)
        }
        return null
    }

    override fun <T> insertAll(itemList: List<T>): Completable? {
        return Completable.fromAction {
            mCategoryDao.insertAll(itemList as List<CategoryTable>)
        }
    }

    override fun <T> insert(item: T): Single<Long> {
        return mCategoryDao.insert(item as CategoryTable)

    }

    override fun <T> update(item: T): Completable {
        return Completable.fromAction {
            mCategoryDao.update(item as CategoryTable)
        }
    }

    override fun <T> delete(item: T): Completable? {
        return Completable.fromAction {
            mCategoryDao.delete(item as CategoryTable)
        }
    }

    override fun <T> deleteAll(itemList: List<T>): Completable? {
        return mCategoryDao.deleteAll(itemList as List<CategoryTable>)
    }

    override fun deleteAll(): Completable? {
        return Completable.fromAction {
            mCategoryDao.deleteAll()
        }
    }
}