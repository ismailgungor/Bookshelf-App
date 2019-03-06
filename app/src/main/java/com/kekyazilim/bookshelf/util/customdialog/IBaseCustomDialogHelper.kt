package com.kekyazilim.bookshelf.util.customdialog

import io.reactivex.subjects.PublishSubject

interface IBaseCustomDialogHelper {

    fun prepareCustomDialog(): IBaseCustomDialogHelper

    fun handleDialogView()

    fun showCustomDialog(): IBaseCustomDialogHelper

    fun dismissCustomDialog(): IBaseCustomDialogHelper

    fun <T> setClickEvent(clickEvent: PublishSubject<T>): IBaseCustomDialogHelper

}