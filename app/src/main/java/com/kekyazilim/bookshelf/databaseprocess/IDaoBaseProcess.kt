package com.kekyazilim.bookshelf.databaseprocess

import io.reactivex.Completable
import io.reactivex.Single

interface IDaoBaseProcess {

    fun <T> insertAll(itemList: List<T>): Completable?

    fun <T> insert(item: T): Single<Long>?

    fun <T> update(item: T): Completable?

    fun <T> delete(item: T): Completable?

    fun <T> deleteAll(itemList: List<T>): Completable?

    fun deleteAll(): Completable?

}