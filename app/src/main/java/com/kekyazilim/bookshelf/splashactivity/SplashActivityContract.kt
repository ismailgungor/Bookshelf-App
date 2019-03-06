package com.kekyazilim.bookshelf.splashactivity

interface SplashActivityContract {

    interface View {

        fun finishActivity()

        fun showErrorMessage()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()
    }
}